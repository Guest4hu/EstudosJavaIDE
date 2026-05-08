# 🧵 Java Threads — Stop, Resume e Suspend (Implementação Segura)

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** Concorrência  
>[[Threads]]

---

## ⚠️ Contexto — Por que os métodos originais foram descontinuados?

No **Java 1** existiam três métodos nativos da classe `Thread`:

|Método (descontinuado)|O que fazia|Por que foi removido|
|---|---|---|
|`Thread.stop()`|Encerrava a thread abruptamente|Liberava locks de forma inconsistente, corrompendo estado de objetos compartilhados|
|`Thread.suspend()`|Pausava a thread sem liberar locks|Causava **deadlock** — outras threads ficavam esperando eternamente por locks nunca liberados|
|`Thread.resume()`|Retomava uma thread suspensa|Sem `suspend()`, perdeu utilidade|

> **Deadlock** = situação em que duas ou mais threads ficam bloqueadas esperando por recursos que nunca serão liberados, travando o sistema indefinidamente.

A partir do **Java 2**, esses métodos foram **deprecados** (`@Deprecated`) e substituídos pela abordagem com `interrupt()`, flags booleanas e os mecanismos `wait()` / `notify()` dentro de blocos `synchronized`.

---

## 🏗️ Estrutura da Classe — Implementação Segura

A classe implementa `Runnable` e controla o ciclo de vida da thread com duas **flags booleanas de estado**:

java

```java
public class StopResumeSuspend implements Runnable {

    private String nome;
    private boolean suspended;   // flag de pausa — controla o suspend/resume
    private boolean foiFinalizada; // flag de encerramento — controla o stop

    public StopResumeSuspend(String nome) {
        this.nome = nome;
        this.suspended = false;     // thread começa ativa (não pausada)
        this.foiFinalizada = false; // thread começa sem sinalização de encerramento
        new Thread(this, nome).start(); // instancia e já inicia a thread
    }
}
```

### Por que iniciar a Thread no construtor?

A Thread é criada e iniciada diretamente no construtor para que, ao instanciar o objeto, a execução já comece automaticamente. Isso é um padrão comum para classes de tarefa autocontida.

> ⚠️ **Atenção:** Iniciar threads no construtor pode ser problemático em hierarquias de herança — a thread pode começar antes da subclasse terminar de inicializar. Para sistemas mais complexos, prefira **fábricas** ou **ExecutorService**.

---

## ▶️ Método `run()` — O Coração da Thread

java

```java
@Override
public void run() {
    System.out.println("Executando thread " + this.nome);

    try {
        for (int i = 0; i < 10; i++) {

            Thread.sleep(500); // simula trabalho — pausa de 500ms entre iterações

            System.out.println("Thread " + this.nome + " contador: " + i);

            synchronized (this) {           // (1) adquire o monitor do objeto
                while (suspended) {         // (2) loop de guarda — protege contra spurious wakeups
                    wait();                 // (3) libera o monitor e dorme até ser notificada
                }
                if (foiFinalizada) {        // (4) checa flag de encerramento
                    break;                  // (5) sai do for, encerra a thread
                }
            }                               // (6) libera o monitor automaticamente
        }
    } catch (InterruptedException e) {
        // InterruptedException é lançada se alguém chamar thread.interrupt() externamente
        Thread.currentThread().interrupt(); // boa prática: restaura o status de interrupção
        System.out.println("Thread " + this.nome + " foi interrompida externamente");
    }

    System.out.println("Thread " + this.nome + " finalizada");
}
```

### Fluxo de execução passo a passo

```
run() inicia
    └── for i = 0 até 9
            ├── Thread.sleep(500ms)        ← simula processamento
            ├── imprime contador
            └── synchronized(this)
                    ├── suspended == true?
                    │       └── wait()     ← libera lock, dorme aqui até notify()
                    ├── suspended == false?
                    │       └── continua o loop normalmente
                    └── foiFinalizada == true?
                            └── break      ← encerra o for
run() termina → imprime "finalizada"
```

### Por que `while (suspended)` e não `if (suspended)`?

O `while` protege contra **spurious wakeups** — situação em que a JVM acorda a thread espontaneamente sem que `notify()` tenha sido chamado (comportamento permitido pela especificação Java). Com `if`, a thread continuaria mesmo ainda devendo estar pausada. Com `while`, ela verifica a condição novamente ao acordar.

---

## ⏸️ Método `suspend()` — Pausar a Thread

java

```java
public synchronized void suspend() {
    this.suspended = true;
    // A thread detectará suspended == true na próxima iteração do for
    // e chamará wait(), pausando-se com segurança
}
```

> ⚠️ **Diferença crucial do `Thread.suspend()` original:**  
> O método original pausava a thread _imediatamente_, sem liberar os locks que ela segurava — causando deadlock.  
> Nossa implementação **seta uma flag** e a thread se pausa **por conta própria** dentro do bloco `synchronized`, liberando o monitor via `wait()`.

---

## ▶️ Método `resume()` — Retomar a Thread

java

```java
public void resume() {
    this.suspended = false;         // desativa a flag de pausa
    synchronized (this) {
        notify();                   // acorda a thread que está em wait()
    }
}
```

**O que `notify()` faz:**

- Acorda **uma** thread que esteja em `wait()` sobre o mesmo monitor (`this`)
- A thread acordada tenta readquirir o lock e, ao conseguir, reavalia o `while (suspended)` — que agora é `false` — e continua a execução

> **`notify()` vs `notifyAll()`:**
> 
> - `notify()` acorda apenas **uma** thread em espera (qual delas fica a cargo da JVM)
> - `notifyAll()` acorda **todas** as threads em espera sobre aquele monitor  
>     Para este caso, com apenas uma thread por objeto, `notify()` é suficiente.

---

## 🛑 Método `stop()` — Encerrar a Thread com Segurança

java

```java
public void stop() {
    this.foiFinalizada = true;      // sinaliza que a thread deve encerrar
    synchronized (this) {
        notify();                   // acorda a thread caso ela esteja em wait()
    }
    // a thread, ao acordar, verifica foiFinalizada == true e executa break
}
```

> **Por que `notify()` aqui também?**  
> Se a thread estiver pausada dentro do `wait()` (suspended == true), sem o `notify()` ela nunca acordaria para verificar `foiFinalizada` e nunca encerraria.

### Comparação: `stop()` seguro vs `interrupt()`

||Nossa implementação (`stop()`)|`thread.interrupt()` nativo|
|---|---|---|
|**Como funciona**|Flag booleana + `notify()`|Seta flag de interrupção na thread|
|**Quando age**|Na próxima iteração do loop|Imediatamente (lança `InterruptedException` em `sleep/wait`)|
|**Controle**|Você define o ponto de parada|A thread deve tratar a exceção|
|**Uso recomendado**|Lógica de negócio controlada|Cancelamento de tarefas de I/O ou espera longa|

---

## 🧪 Classe de Teste — Demonstração Completa

java

```java
private static void testeStopResumeSuspend() {
    StopResumeSuspend thread1 = new StopResumeSuspend("Thread-1");
    StopResumeSuspend thread2 = new StopResumeSuspend("Thread-2");

    try {
        Thread.sleep(2000);       // aguarda 2s — deixa as threads rodarem livremente

        thread1.suspend();
        System.out.println(">>> Thread 1 PAUSADA");

        Thread.sleep(2000);       // aguarda 2s — thread1 pausada, thread2 continua

        thread1.resume();
        System.out.println(">>> Thread 1 RETOMADA");

        Thread.sleep(2000);       // aguarda 2s — ambas rodando

        thread2.stop();
        System.out.println(">>> Thread 2 ENCERRADA");

    } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // restaura o status de interrupção
        throw new RuntimeException("Thread principal interrompida", e);
    }
}
```

### Linha do tempo de execução

```
t=0s    → thread1 e thread2 iniciam e rodam livremente
t=2s    → thread1.suspend()  → thread1 pausa, thread2 continua
t=4s    → thread1.resume()   → thread1 retoma, ambas rodam
t=6s    → thread2.stop()     → thread2 encerra no próximo ciclo
t=~10s  → thread1 termina naturalmente (loop de 10 iterações × 500ms)
```

---

## 📋 Resumo dos Conceitos-Chave

|Conceito|O que é|Quando usar|
|---|---|---|
|`synchronized(this)`|Adquire o **monitor** do objeto — garante acesso exclusivo ao bloco|Sempre que acessar estado compartilhado entre threads|
|`wait()`|Libera o monitor e dorme até ser acordada por `notify()`|Dentro de `synchronized`, para pausar a thread com segurança|
|`notify()`|Acorda uma thread em `wait()` sobre o mesmo monitor|Ao mudar uma condição que outra thread está esperando|
|`while (flag)` + `wait()`|Padrão _guarded suspension_ — espera segura com recheck da condição|Sempre que usar `wait()`, para proteger de spurious wakeups|
|Flag booleana (`volatile`)|Sinaliza mudança de estado entre threads|Para comunicação simples entre threads sem sincronização pesada|

> ⚠️ **Melhoria recomendada para produção:** As flags `suspended` e `foiFinalizada` deveriam ser declaradas como `volatile` para garantir visibilidade entre threads sem depender apenas do `synchronized`:
> 
> java
> 
> ```java
> private volatile boolean suspended;
> private volatile boolean foiFinalizada;
> ```
> 
> `volatile` garante que toda thread sempre leia o valor mais recente da variável da memória principal, evitando problemas de cache de CPU.

---

## 🔗 Mapa de Conexões

```
Threads — Stop, Resume, Suspend
│
├── Base
│   ├── [[Java-Fundamentos-Aula-1]] → seção Concurrency API
│   └── [[Java Thread — Ciclo de Vida]]
│
├── Mecanismos utilizados
│   ├── [[synchronized e Monitor]]
│   ├── [[wait() e notify()]]
│   └── [[volatile — Visibilidade entre Threads]]
│
├── Problemas evitados
│   ├── [[Deadlock]]
│   └── [[Race Condition]]
│
└── Alternativas modernas
    ├── [[ExecutorService e Future]]
    └── [[ReentrantLock e Condition]]
```



# 🔒 Java Threads — Deadlocks e Exercício Semáforo

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Threads]] 

---

## 🧱 O que é um Deadlock?

**Deadlock** (ou _impasse_) é uma situação em que duas ou mais threads ficam bloqueadas **permanentemente**, cada uma esperando por um recurso que está sendo segurado pela outra — e nenhuma consegue avançar.

É um dos bugs mais perigosos em sistemas concorrentes: **não lança exceção, não gera erro visível** — o programa simplesmente trava silenciosamente.

### Condições para um Deadlock ocorrer

Para que um deadlock aconteça, as **quatro condições de Coffman** precisam ser satisfeitas simultaneamente:

|Condição|O que significa|
|---|---|
|**Exclusão mútua**|O recurso só pode ser usado por uma thread por vez (`synchronized`)|
|**Posse e espera**|A thread segura um recurso enquanto espera por outro|
|**Sem preempção**|Nenhuma thread pode forçar outra a liberar um recurso|
|**Espera circular**|Thread A espera por Thread B, que espera por Thread A|

> Quebrar **qualquer uma** dessas condições elimina o deadlock.

---

## ☠️ Exemplo de Deadlock

### Diagrama do impasse

```
Thread 1 segura → [RECURSO_1]    e espera por → [RECURSO_2]
Thread 2 segura → [RECURSO_2]    e espera por → [RECURSO_1]
                         ↑_____________________________|
                              Espera circular = DEADLOCK
```

### Código — Thread 1

java

```java
private static final Object RECURSO_1 = new Object();
private static final Object RECURSO_2 = new Object();

Thread thread1 = new Thread(() -> {
    synchronized (RECURSO_1) {                          // (1) adquire lock do RECURSO_1
        System.out.println("Thread 1: bloqueou RECURSO_1");

        try {
            Thread.sleep(100);                          // simula processamento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Thread 1: tentando bloquear RECURSO_2...");
        synchronized (RECURSO_2) {                      // (2) tenta adquirir RECURSO_2
            // ← nunca chega aqui: RECURSO_2 está com Thread 2
            System.out.println("Thread 1: bloqueou RECURSO_2");
        }
    }
});
```

### Código — Thread 2

java

```java
Thread thread2 = new Thread(() -> {
    synchronized (RECURSO_2) {                          // (1) adquire lock do RECURSO_2
        System.out.println("Thread 2: bloqueou RECURSO_2");

        try {
            Thread.sleep(100);                          // simula processamento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Thread 2: tentando bloquear RECURSO_1...");
        synchronized (RECURSO_1) {                      // (2) tenta adquirir RECURSO_1
            // ← nunca chega aqui: RECURSO_1 está com Thread 1
            System.out.println("Thread 2: bloqueou RECURSO_1");
        }
    }
});
```

### O que acontece em tempo de execução

```
t=0ms   Thread 1 adquire RECURSO_1 ✅
t=0ms   Thread 2 adquire RECURSO_2 ✅
t=100ms Thread 1 tenta adquirir RECURSO_2 → BLOQUEADA ⏸️
t=100ms Thread 2 tenta adquirir RECURSO_1 → BLOQUEADA ⏸️
t=∞     Ambas aguardam para sempre → DEADLOCK 💀
```

> ⚠️ **Correção no código original:** O `System.out.println` da Thread 2 dizia `"bloqueou o recurso 1"` quando deveria ser `"bloqueou o recurso 2"` — o print estava invertido em relação ao `synchronized`.

---

## 🛡️ Como Prevenir Deadlocks

### 1. Ordem consistente de aquisição de locks

A forma mais simples: **sempre adquira locks na mesma ordem** em todas as threads.

java

```java
// ✅ CORRETO — ambas as threads adquirem na mesma ordem: RECURSO_1 → RECURSO_2
Thread thread1 = new Thread(() -> {
    synchronized (RECURSO_1) {
        synchronized (RECURSO_2) {
            // trabalho seguro
        }
    }
});

Thread thread2 = new Thread(() -> {
    synchronized (RECURSO_1) {   // mesma ordem: RECURSO_1 primeiro
        synchronized (RECURSO_2) {
            // trabalho seguro
        }
    }
});
```

### 2. Usar `tryLock()` com timeout (ReentrantLock)

java

```java
import java.util.concurrent.locks.ReentrantLock;

ReentrantLock lock1 = new ReentrantLock();
ReentrantLock lock2 = new ReentrantLock();

// Tenta adquirir o lock por no máximo 50ms — se não conseguir, desiste
if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
    try {
        if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
            try {
                // trabalho seguro
            } finally {
                lock2.unlock();
            }
        }
    } finally {
        lock1.unlock();
    }
}
```

### 3. Usar estruturas thread-safe da `java.util.concurrent`

java

```java
// Em vez de synchronized manual, use estruturas já projetadas para concorrência
ConcurrentHashMap<String, Integer> mapa = new ConcurrentHashMap<>();
BlockingQueue<String> fila = new LinkedBlockingQueue<>();
```

---

## 🚦 Exercício — Semáforo com Threads

O exercício simula o comportamento de um **semáforo de trânsito** usando três threads, cada uma representando uma cor (Verde, Amarelo, Vermelho) e seu respectivo texto de instrução.

### Classe `ThreadSemaforo`

java

```java
public class ThreadSemaforo implements Runnable {

    private String cor;
    private String texto;
    private volatile boolean suspended;      // volatile garante visibilidade entre threads
    private volatile boolean foiFinalizada;  // volatile garante visibilidade entre threads

    public ThreadSemaforo(String cor, String texto) {
        this.cor = cor;
        this.texto = texto;
        this.suspended = false;
        this.foiFinalizada = false;
        new Thread(this, cor).start();
    }

    @Override
    public void run() {
        System.out.println("Iniciando sinal: " + cor);

        while (!foiFinalizada) {                // loop principal — roda até stop() ser chamado
            System.out.println("[" + cor + "] " + this.texto);

            synchronized (this) {
                while (suspended) {             // guarded suspension — protege de spurious wakeups
                    try {
                        this.wait();            // libera o monitor e aguarda notify()
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // restaura status de interrupção
                        return; // encerra a thread de forma limpa
                    }
                }
            }
        }

        System.out.println("Sinal " + cor + " encerrado.");
    }

    public synchronized void suspend() {
        this.suspended = true;
    }

    public synchronized void resume() {
        this.suspended = false;
        notify();   // acorda a thread parada no wait()
    }

    public synchronized void stop() {
        this.foiFinalizada = true;
        notify();   // acorda a thread caso esteja em wait(), para que o while possa terminar
    }
}
```

> ⚠️ **Melhoria aplicada:** As flags `suspended` e `foiFinalizada` foram marcadas como `volatile`.  
> Sem `volatile`, a JVM pode cachear o valor da flag em um registrador da CPU, fazendo com que a thread nunca veja a atualização feita por outra thread.

### Diferença em relação à aula anterior (`StopResumeSuspend`)

||`StopResumeSuspend`|`ThreadSemaforo`|
|---|---|---|
|**Loop principal**|`for` com contador (0 a 9)|`while (!foiFinalizada)` — infinito até stop|
|**sleep()**|Dentro do `for`, fora do `synchronized`|Sem sleep — imprime o texto continuamente|
|**Finalidade**|Demonstrar controle de ciclo|Simular lógica de negócio contínua (semáforo)|

---

### Método `main` — Orquestrando o Semáforo

java

```java
public static void main(String[] args) {
    ThreadSemaforo verde    = new ThreadSemaforo("Verde",    "Pode andar");
    ThreadSemaforo amarelo  = new ThreadSemaforo("Amarelo",  "Atenção — vai fechar");
    ThreadSemaforo vermelho = new ThreadSemaforo("Vermelho", "Fechado — aguarde");

    try {
        Thread.sleep(500);       // aguarda as 3 threads iniciarem

        // Fase 1: apenas Verde ativa
        amarelo.suspend();
        vermelho.suspend();
        Thread.sleep(5000);      // verde ativa por 5s

        // Fase 2: apenas Amarelo ativo
        verde.suspend();
        amarelo.resume();
        Thread.sleep(1000);      // amarelo ativo por 1s

        // Fase 3: apenas Vermelho ativo
        amarelo.suspend();
        vermelho.resume();
        Thread.sleep(3000);      // vermelho ativo por 3s

        // Fase 4: volta para Verde
        vermelho.suspend();
        verde.resume();
        Thread.sleep(5000);      // verde ativo novamente por 5s

        // Encerra todas as threads
        verde.stop();
        amarelo.stop();
        vermelho.stop();

    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Thread principal interrompida", e);
    }
}
```

### Linha do tempo de execução

```
t=0s      → Verde, Amarelo e Vermelho iniciam
t=0.5s    → Amarelo e Vermelho suspensos → apenas Verde imprime
t=5.5s    → Verde suspensa, Amarelo retoma → apenas Amarelo imprime
t=6.5s    → Amarelo suspensa, Vermelho retoma → apenas Vermelho imprime
t=9.5s    → Vermelho suspensa, Verde retoma → apenas Verde imprime
t=14.5s   → verde.stop() / amarelo.stop() / vermelho.stop() → todas encerram
```

---

## 📋 Resumo — Deadlock vs Semáforo

||Deadlock|Semáforo|
|---|---|---|
|**Objetivo**|Demonstrar o problema|Demonstrar a solução com controle de estado|
|**Resultado**|Programa trava para sempre|Execução controlada e previsível|
|**Mecanismo**|`synchronized` aninhado em ordem inversa|`synchronized` + flags + `wait/notify`|
|**Solução**|Ordem consistente de locks / `tryLock`|Padrão _guarded suspension_ já aplicado|

---

## 🔗 Mapa de Conexões

```
Deadlocks e Semáforo
│
├── Pré-requisito
│   └── [[Java-Threads-Stop-Resume-Suspend]]
│
├── Conceitos de Deadlock
│   ├── [[synchronized e Monitor]]
│   ├── [[Race Condition]]
│   └── Condições de Coffman (4 condições)
│
├── Soluções
│   ├── Ordem consistente de locks
│   ├── [[ReentrantLock e tryLock]]
│   └── [[java.util.concurrent — Estruturas Thread-Safe]]
│
└── Padrões aplicados no Semáforo
    ├── Guarded Suspension (while + wait)
    ├── [[volatile — Visibilidade entre Threads]]
    └── [[wait() e notify()]]
```


# 🔤 Java String — Construtores, Memória e String Pool

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]
---

## 🧭 Visão Geral

`String` em Java **não é um tipo primitivo** — é uma **classe** (`java.lang.String`). Porém, por ser tão usada, a linguagem oferece uma sintaxe simplificada para declará-la (sem `new`).

Existem diferentes formas de construir uma String, e cada uma tem um comportamento distinto na memória.

---

## 🏗️ Formas de Declarar uma String

### 1. Literal (atribuição simples)

java

```java
String java = "Java";
```

- A String `"Java"` é armazenada no **String Pool** (explicado abaixo)
- É a forma mais comum e eficiente

---

### 2. Construtor a partir de outra String

java

```java
String java1 = new String(java);
```

- Usa o operador `new` → cria uma **nova instância** no **Heap**
- O conteúdo é igual à String `java`, mas ocupa um **espaço diferente na memória**
- Evite sem necessidade — é menos eficiente que o literal

---

### 3. Construtor a partir de `char[]` (array de caracteres)

java

```java
char[] charJava = {'J', 'A', 'V', 'A'};
String java2 = new String(charJava);
// resultado: "JAVA"
```

- Percorre o array de `char` e concatena os caracteres em uma String
- Cria uma nova instância no **Heap**
- Útil quando se trabalha com dados de baixo nível (ex: leitura de bytes, criptografia)

---

### 4. Construtor com `char[]` + offset e count (substring de array)

java

```java
char[] abcDe = {'A', 'B', 'C', 'D', 'E'};
String abc = new String(abcDe, 0, 3);
// resultado: "ABC"
```

**Parâmetros:**

|Parâmetro|Significado|Valor no exemplo|
|---|---|---|
|`char[]`|Array de origem|`{'A','B','C','D','E'}`|
|`offset`|Índice inicial (inclusivo)|`0` → começa em `'A'`|
|`count`|Quantidade de caracteres|`3` → pega `'A'`, `'B'`, `'C'`|

> ⚠️ **Correção no código original:** O array foi declarado como `abcDe` mas chamado como `abcdef` no construtor — o nome da variável deve ser consistente.

---

### 5. Atribuição por referência

java

```java
String java3 = "Java";
String java4 = java3;
```

- `java4` **não cria uma nova String** — aponta para o **mesmo objeto** que `java3`
- Ambas referenciam o mesmo endereço de memória no String Pool

---

## 🧠 Como a Memória Funciona — String Pool vs Heap

Esta é a parte mais importante para entender o comportamento de Strings em Java.

```
┌─────────────────────────────────────────────────────────┐
│                        JVM MEMORY                       │
│                                                         │
│  ┌──────────────────────────────┐                       │
│  │         STRING POOL          │                       │
│  │    (área especial do Heap)   │                       │
│  │                              │                       │
│  │  "Java" ←── java (literal)   │                       │
│  │     ↑                        │                       │
│  │     └────── java3, java4     │  ← mesma referência  │
│  └──────────────────────────────┘                       │
│                                                         │
│  ┌──────────────────────────────┐                       │
│  │            HEAP              │                       │
│  │                              │                       │
│  │  [String "Java"] ← java1    │  ← new String(java)  │
│  │  [String "JAVA"] ← java2    │  ← new String(char[])│
│  └──────────────────────────────┘                       │
└─────────────────────────────────────────────────────────┘
```

### String Pool — O que é?

O **String Pool** (ou _Intern Pool_) é uma região especial dentro do Heap onde a JVM armazena Strings literais para **reutilização**.

**Regra:** Se você declara `"Java"` duas vezes como literal, a JVM **não cria dois objetos** — ambas as variáveis apontam para o **mesmo objeto** no pool.

java

```java
String a = "Java";
String b = "Java";

System.out.println(a == b);      // true  — mesma referência no pool
System.out.println(a.equals(b)); // true  — mesmo conteúdo
```

### `new String()` — Fora do Pool

Quando você usa `new`, a JVM **sempre cria um novo objeto no Heap**, mesmo que uma String idêntica já exista no pool.

java

```java
String a = "Java";
String b = new String("Java");

System.out.println(a == b);      // false — referências diferentes (pool vs heap)
System.out.println(a.equals(b)); // true  — conteúdo igual
```

> ⚠️ **Regra de ouro:** Para **comparar conteúdo** de Strings, use sempre `equals()`.  
> O operador `==` compara **referências de memória**, não o texto em si.  
> Relacionado: `[[equals() vs == em Java]]`

---

## 📊 Comparativo Geral

|Forma de declaração|Onde fica na memória|Cria novo objeto?|Exemplo|
|---|---|---|---|
|`"texto"` (literal)|String Pool|Só se não existir|`String s = "Java"`|
|`= outraString` (referência)|Aponta para o Pool|❌ Não|`String s2 = s`|
|`new String("texto")`|Heap|✅ Sempre|`new String("Java")`|
|`new String(char[])`|Heap|✅ Sempre|`new String(charArray)`|
|`new String(char[], offset, count)`|Heap|✅ Sempre|`new String(arr, 0, 3)`|

---

## 💡 Boas Práticas

java

```java
// ✅ Prefira literais — eficiente, usa o pool
String nome = "João";

// ✅ Use equals() para comparar conteúdo
if (nome.equals("João")) { ... }

// ✅ Use intern() para forçar uma String do Heap a entrar no pool
String pooled = new String("Java").intern(); // agora está no pool

// ❌ Evite new String() sem motivo — desperdiça memória
String ruim = new String("Java");

// ❌ Nunca compare Strings com == para verificar conteúdo
if (nome == "João") { ... } // pode retornar false mesmo com mesmo texto
```

---

## 🔗 Mapa de Conexões

```
Java String — Construtores e Memória
│
├── Base
│   └── [[Java-Fundamentos-Aula-1]]
│
├── Memória
│   ├── [[Java Heap e Stack — Gerenciamento de Memória]]
│   └── String Pool (área especial do Heap)
│
├── Armadilhas comuns
│   ├── [[equals() vs == em Java]]
│   └── new String() vs literal
│
└── Relacionados
    ├── [[Java Tipos Primitivos e Wrappers]]
    └── [[StringBuilder e StringBuffer]]
```

# 🔗 Java String — Concatenação, Imutabilidade e Boas Práticas

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## 🧭 O que é Concatenação?

Concatenação é a **junção de duas ou mais Strings** em uma única String resultante.

java

```java
String curso = "Curso ";
String java  = "Java";

System.out.println(curso + java); // output: "Curso Java"
```

O operador `+` é sobrecarregado em Java especificamente para Strings — ao detectar que um dos operandos é `String`, ele realiza a concatenação em vez de uma soma numérica.

---

## ➕ Operador `+` com Strings e Números

Este é um ponto de atenção importante: o comportamento do `+` muda dependendo da **ordem e do uso de parênteses**.

java

```java
// Com parênteses → operação matemática é executada PRIMEIRO
String resultado1 = "Resultado de 2+2 = " + (2 + 2);
// output: "Resultado de 2+2 = 4"

// Sem parênteses → avaliação da ESQUERDA para DIREITA
String resultado2 = "Resultado de 2+2 = " + 2 + 2;
// output: "Resultado de 2+2 = 22"
```

### Por que o segundo vira `"22"` e não `"4"`?

Java avalia expressões da **esquerda para a direita**. Quando o `+` encontra uma String no lado esquerdo, todos os operandos seguintes são tratados como concatenação:

```
"Resultado de 2+2 = " + 2   →  "Resultado de 2+2 = 2"   (String + int = String)
"Resultado de 2+2 = 2"  + 2   →  "Resultado de 2+2 = 22"  (String + int = String)
```

Com parênteses, a expressão `(2 + 2)` é resolvida primeiro como operação matemática (`int + int = 4`), e só depois é concatenada com a String.

> ⚠️ **Regra:** Use parênteses sempre que quiser garantir que a operação matemática seja executada antes da concatenação.

---

## 🔄 `String.valueOf()` — Convertendo Outros Tipos em String

O método estático `String.valueOf()` converte qualquer tipo primitivo ou objeto em sua representação como `String`.

java

```java
String umComoString  = String.valueOf(1);       // int    → "1"
String piComoString  = String.valueOf(3.14);    // double → "3.14"
String boolString    = String.valueOf(true);    // boolean → "true"
String charString    = String.valueOf('A');     // char   → "A"
```

### Outras formas equivalentes

java

```java
int numero = 42;

String s1 = String.valueOf(numero);      // forma explícita — mais legível ✅
String s2 = Integer.toString(numero);   // via wrapper da classe Integer
String s3 = "" + numero;                // concatenação implícita — funciona, mas menos claro ⚠️
```

> **Quando usar:** Sempre que precisar passar um número ou booleano para um método que espera `String`, ou ao construir uma String a partir de dados de diferentes tipos.

---

## 🧱 Strings são Imutáveis (_Immutable_)

Este é um dos conceitos mais importantes sobre Strings em Java:

> **Uma vez criada, o conteúdo de uma String nunca muda.**

Quando você "modifica" uma String, na verdade está **criando um novo objeto** e descartando o antigo.

### O problema da concatenação com `+=`

java

```java
String concat = "";
concat += "parte 1 ";   // cria String "parte 1 "         — objeto anterior descartado
concat += "parte 2 ";   // cria String "parte 1 parte 2 " — objeto anterior descartado
concat += "parte 3 ";   // cria String "parte 1 parte 2 parte 3 " — ...
concat += "parte 4 ";   // cria String "parte 1 parte 2 parte 3 parte 4"
```

### O que acontece na memória a cada `+=`

```
concat = ""
  ↓
[novo objeto] "parte 1 "            → "" torna-se lixo 🗑️
  ↓
[novo objeto] "parte 1 parte 2 "    → "parte 1 " torna-se lixo 🗑️
  ↓
[novo objeto] "parte 1 parte 2 parte 3 "  → lixo 🗑️
  ↓
[novo objeto] "parte 1 parte 2 parte 3 parte 4"  → lixo 🗑️
```

Cada objeto descartado vai para o **Garbage Collector** — o mecanismo da JVM que libera memória de objetos sem referência.  
Com poucas concatenações isso é aceitável. Em **loops com muitas iterações**, essa prática se torna um **sério problema de performance e memória**.

---

## ✅ Solução — `StringBuilder`

Para concatenações repetidas ou dentro de loops, use **`StringBuilder`**:

java

```java
// ❌ MÁ PRÁTICA — cria N objetos String desnecessários
String concat = "";
for (int i = 0; i < 1000; i++) {
    concat += "item " + i;  // cria 1000+ objetos descartados
}

// ✅ BOA PRÁTICA — StringBuilder modifica o mesmo buffer interno
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("item ").append(i);  // sem criação de objetos intermediários
}
String resultado = sb.toString();  // converte para String apenas no final
```

### Principais métodos do `StringBuilder`

java

```java
StringBuilder sb = new StringBuilder();

sb.append("Java");           // adiciona ao final        → "Java"
sb.append(" é ótimo");       // adiciona ao final        → "Java é ótimo"
sb.insert(4, " ainda");      // insere na posição 4      → "Java ainda é ótimo"
sb.delete(4, 10);            // remove do índice 4 ao 10 → "Java é ótimo"
sb.reverse();                // inverte a String         → "otimo é avaJ"
sb.replace(0, 5, "Python");  // substitui trecho         → "Python é avaJ"

String final = sb.toString(); // converte para String imutável
```

### `StringBuilder` vs `StringBuffer`

||`StringBuilder`|`StringBuffer`|
|---|---|---|
|**Thread-safe**|❌ Não|✅ Sim (métodos `synchronized`)|
|**Performance**|✅ Mais rápido|⚠️ Mais lento (overhead do sync)|
|**Quando usar**|Uso geral — single-thread|Ambientes multi-thread|

> **Regra prática:** Use `StringBuilder` por padrão. Use `StringBuffer` somente se a instância for compartilhada entre múltiplas threads.  
> Relacionado: `[[Java-Threads-Deadlocks-e-Semaforo]]`

---

## 📊 Resumo Comparativo — Formas de Concatenar

|Forma|Cria novos objetos?|Quando usar|
|---|---|---|
|`+` (literal)|Sim — compilador pode otimizar|Concatenações simples, fora de loops|
|`+=` em loop|✅ Sim — a cada iteração|❌ Evitar em loops|
|`String.valueOf()`|Sim — apenas 1 objeto|Converter primitivos para String|
|`StringBuilder.append()`|❌ Não (buffer interno)|✅ Loops, muitas concatenações|
|`StringBuffer.append()`|❌ Não (thread-safe)|Ambientes multi-thread|

---

## 💡 Boas Práticas — Concatenação

java

```java
// ✅ Simples — para poucas concatenações fora de loops
String msg = "Olá, " + nome + "! Bem-vindo ao " + curso + ".";

// ✅ StringBuilder — para loops ou muitas concatenações
StringBuilder sb = new StringBuilder();
for (String parte : listaDeParts) {
    sb.append(parte);
}
String resultado = sb.toString();

// ✅ String.format() — para mensagens com formatação
String log = String.format("Usuário %s realizou login às %s", usuario, horario);

// ✅ Java 15+ — Text Blocks para Strings multilinha
String json = """
        {
            "nome": "João",
            "curso": "Java"
        }
        """;

// ❌ Evitar — concatenação com += em loop
String ruim = "";
for (int i = 0; i < 1000; i++) {
    ruim += i; // 1000 objetos criados e descartados
}
```


# 🔍 Java String — Extraindo Caracteres

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## 🧭 Visão Geral

Uma `String` em Java é internamente representada como um **array de `char`**. A linguagem oferece quatro métodos principais para acessar ou extrair esses caracteres:

|Método|Retorna|Extrai|
|---|---|---|
|`charAt(int index)`|`char`|Um único caractere por índice|
|`getChars(...)`|`void`|Trecho da String em um `char[]` existente|
|`getBytes(...)`|`void`|Trecho da String em um `byte[]` (valor ASCII)|
|`toCharArray()`|`char[]`|A String inteira como novo array de `char`|

---

## 1️⃣ `charAt(int index)` — Acessar um Caractere por Posição

java

```java
public class MetodoCharAt {
    public static void main(String[] args) {
        String java = "Java";

        for (int i = 0; i < java.length(); i++) {  // length(), não lenght()
            System.out.println(java.charAt(i));
        }
    }
}
```

**Output:**

```
J
a
v
a
```

### Como funciona

`charAt(i)` retorna o `char` na posição `i` do array interno da String. Os índices sempre começam em `0`:

```
String:  J    a    v    a
Índice:  0    1    2    3
```

java

```java
java.charAt(0)  // → 'J'
java.charAt(1)  // → 'a'
java.charAt(2)  // → 'v'
java.charAt(3)  // → 'a'
java.charAt(4)  // → ⚠️ StringIndexOutOfBoundsException — índice inválido
```

> ⚠️ **Correção no código original:** O método estava escrito como `java.lenght()` — o correto é `java.length()` (sem o `h` antes do `t`). Esse é um dos erros de digitação mais comuns em Java.

> ⚠️ **Cuidado com `StringIndexOutOfBoundsException`:** Acessar um índice maior ou igual a `length()` lança essa exceção em tempo de execução. O loop com `i < java.length()` já protege contra isso.

---

## 2️⃣ `getChars()` — Copiar Trecho para um `char[]`

java

```java
public class GetChars {
    public static void main(String[] args) {
        String java    = "Java";
        char[] destino = new char[3];           // array destino com espaço para 3 chars

        java.getChars(0, 3, destino, 0);        // copia chars para o array destino

        System.out.println(destino);            // → Jav
        System.out.println(new String(destino)); // → Jav
    }
}
```

### Assinatura do método

java

```java
String.getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
```

|Parâmetro|Significado|Valor no exemplo|
|---|---|---|
|`srcBegin`|Índice inicial na String (inclusivo)|`0` → começa em `'J'`|
|`srcEnd`|Índice final na String (**exclusivo**)|`3` → pega até `'v'`, não inclui `'a'`|
|`dst`|Array de destino (`char[]`)|`destino`|
|`dstBegin`|Posição inicial no array destino|`0` → começa a preencher do início|

```
String "Java":   J(0)  a(1)  v(2)  a(3)
                 ↑________________↑
                srcBegin=0      srcEnd=3 (exclusivo)

destino[]:   J    a    v
             0    1    2
```

> ⚠️ **`srcEnd` é exclusivo** — `getChars(0, 3, ...)` copia os índices `0`, `1` e `2`, mas não o `3`. Para copiar a String inteira `"Java"` (4 chars), use `srcEnd = 4` e um `char[]` de tamanho `4`.

---

## 3️⃣ `getBytes()` — Copiar Trecho como Valores ASCII (`byte[]`)

java

```java
public class GetBytes {
    public static void main(String[] args) {
        String java      = "Java";
        byte[] javaBytes = new byte[4];

        java.getBytes(0, 4, javaBytes, 0);

        System.out.println(Arrays.toString(javaBytes));
        // → [74, 97, 118, 97]
    }
}
```

> ⚠️ **`getBytes(int, int, byte[], int)` está depreciado desde o Java 1.1.**  
> A forma moderna e correta é:
> 
> java
> 
> ```java
> import java.nio.charset.StandardCharsets;
> 
> byte[] javaBytes = java.getBytes(StandardCharsets.UTF_8);
> System.out.println(Arrays.toString(javaBytes));
> // → [74, 97, 118, 97]
> ```

### Por que `Arrays.toString()` é necessário?

Imprimir um array diretamente com `System.out.println(javaBytes)` não exibe os valores — exibe a **referência de memória** do objeto (algo como `[B@6d06d69c`). O `Arrays.toString()` formata o conteúdo corretamente.

java

```java
System.out.println(javaBytes);                  // → [B@6d06d69c  (referência — inútil) ❌
System.out.println(Arrays.toString(javaBytes)); // → [74, 97, 118, 97]  ✅
```

### O que são esses números? — Tabela ASCII

Cada `byte` retornado corresponde ao valor numérico do caractere na **tabela ASCII**:

|Char|Decimal (ASCII)|Hexadecimal|
|---|---|---|
|`J`|74|0x4A|
|`a`|97|0x61|
|`v`|118|0x76|
|`a`|97|0x61|

> **Onde `getBytes()` é usado na prática:**
> 
> - Serialização e transmissão de dados em rede
> - Criptografia e hashing (`MessageDigest` para MD5/SHA-256)
> - Leitura e escrita de arquivos binários
> - Codificação/decodificação Base64

---

## 4️⃣ `toCharArray()` — Converter a String Inteira em `char[]`

java

```java
public class ToCharArray {
    public static void main(String[] args) {
        String java      = "Java";
        char[] javaChars = java.toCharArray();   // → ['J', 'a', 'v', 'a']

        for (char c : javaChars) {
            System.out.println(c);
        }
    }
}
```

- Retorna um **novo** `char[]` com todos os caracteres da String
- Mais simples que `getChars()` — não precisa de array pré-alocado nem de índices
- O array retornado é **independente** da String original — modificá-lo não afeta a String (imutável)

java

```java
char[] chars = "Java".toCharArray();
chars[0] = 'X';                      // modifica o array
System.out.println(new String(chars)); // → "Xava"
System.out.println("Java");            // → "Java" — String original intacta ✅
```

---

## 📊 Comparativo dos Quatro Métodos

|Método|Retorno|Extrai quantos?|Array pré-alocado?|Depreciado?|
|---|---|---|---|---|
|`charAt(i)`|`char`|1 por chamada|❌|❌|
|`getChars(s, e, dst, d)`|`void`|Trecho `[s, e)`|✅ `char[]`|❌|
|`getBytes(Charset)`|`byte[]`|String inteira|❌ (cria novo)|❌ (forma moderna)|
|`getBytes(s, e, dst, d)`|`void`|Trecho `[s, e)`|✅ `byte[]`|⚠️ Sim (Java 1.1)|
|`toCharArray()`|`char[]`|String inteira|❌ (cria novo)|❌|

---

## 💡 Quando usar cada um

java

```java
// ✅ charAt — acessar posição específica ou iterar com índice
char inicial = nome.charAt(0);

// ✅ toCharArray — iterar toda a String com enhanced for
for (char c : palavra.toCharArray()) { ... }

// ✅ getChars — copiar trecho para um buffer char[] já alocado
java.getChars(0, 3, buffer, posicaoNoBuffer);

// ✅ getBytes(Charset) — serialização, rede, criptografia (forma moderna)
byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);

// ❌ Evitar — forma depreciada do getBytes
java.getBytes(0, 3, byteArray, 0);
```

---

# ⚖️ Java String — Comparação de Strings

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## 🧭 Visão Geral

Java oferece diferentes formas de comparar Strings, cada uma com uma finalidade específica. Escolher a errada é uma das fontes de bug mais comuns em Java.

|Método|Compara|Case-sensitive?|
|---|---|---|
|`==`|Referência de memória|—|
|`equals()`|Conteúdo exato|✅ Sim|
|`equalsIgnoreCase()`|Conteúdo ignorando maiúsculas|❌ Não|
|`regionMatches()`|Trecho de uma String com outra|Configurável|
|`startsWith()` / `endsWith()`|Prefixo ou sufixo|✅ Sim|
|`compareTo()`|Ordem lexicográfica (ASCII)|✅ Sim|
|`compareToIgnoreCase()`|Ordem lexicográfica sem distinção de caso|❌ Não|

---

## 1️⃣ `equals()` — Comparar Conteúdo

java

```java
String ola  = "Ola";
String ola1 = "OLA";
String ola3 = "Ola";

System.out.println(ola.equals(ola1)); // false — conteúdo diferente ("Ola" ≠ "OLA")
System.out.println(ola.equals(ola3)); // true  — conteúdo idêntico
```

> **Por que usar `equals()` e não `==` para Strings?**  
> `String` é uma **classe** (objeto), não um tipo primitivo. O operador `==` em Java compara **referências de memória** (se os dois ponteiros apontam para o mesmo endereço), não o conteúdo. `equals()` foi implementado na classe `String` para comparar **caractere por caractere**.

> ⚠️ **Correção no código original:** O código usava `ola2` nas chamadas de `equals()`, mas a variável declarada era `ola1`. Inconsistência de nome corrigida.

---

## 2️⃣ `==` — Comparar Referências de Memória

### Caso 1: Literais — String Pool

java

```java
String ola  = "Ola";
String ola3 = "Ola";

System.out.println(ola == ola3); // true
```

**Por que retorna `true`?**

O compilador Java mantém um **String Pool**. Ao detectar que `"Ola"` já existe no pool, ele **não cria um segundo objeto** — `ola3` aponta para o mesmo endereço de `ola`.

```
String Pool:
  "Ola" ←── ola
     ↑
     └─────── ola3    (mesma referência → == retorna true)
```

### Caso 2: `new String()` — Fora do Pool

java

```java
String ola  = "Ola";
String ola3 = new String("Ola");

System.out.println(ola == ola3); // false
```

`new String()` **sempre cria um novo objeto no Heap**, fora do pool — mesmo que o conteúdo seja idêntico. O `==` compara endereços distintos e retorna `false`.

```
String Pool:
  "Ola" ←── ola

Heap:
  [String "Ola"] ←── ola3    (endereço diferente → == retorna false)
```

### Resumo: `==` vs `equals()`

java

```java
String a = "Ola";
String b = "Ola";
String c = new String("Ola");

a == b          // true  — mesmo endereço no pool
a == c          // false — endereços diferentes (pool vs heap)
a.equals(b)     // true  — mesmo conteúdo ✅
a.equals(c)     // true  — mesmo conteúdo ✅
```

> ⚠️ **Regra de ouro:** Use **sempre `equals()`** para comparar conteúdo de Strings. Reserve `==` para verificar se duas variáveis apontam para o **exato mesmo objeto** (uso raro com Strings).

---

## 3️⃣ `equalsIgnoreCase()` — Comparar Ignorando Maiúsculas/Minúsculas

java

```java
String ola  = "Ola";
String ola3 = new String("OLA");

System.out.println(ola.equalsIgnoreCase(ola3)); // true
```

Java é **case-sensitive** por padrão — `"Ola"` e `"OLA"` são diferentes para `equals()`. O `equalsIgnoreCase()` normaliza os casos antes de comparar, tornando a comparação insensível a maiúsculas e minúsculas.

java

```java
"java".equalsIgnoreCase("JAVA")   // true
"java".equalsIgnoreCase("Java")   // true
"java".equalsIgnoreCase("jAvA")   // true
"java".equals("JAVA")             // false — case-sensitive
```

> **Uso prático:** Validação de entrada de usuário, comparação de comandos, busca insensível a maiúsculas.

java

```java
// ✅ Verificar comando sem depender de como o usuário digitou
String entrada = scanner.nextLine();
if (entrada.equalsIgnoreCase("sair")) {
    System.out.println("Encerrando...");
}
```

---

## 4️⃣ `regionMatches()` — Comparar Trechos de Strings

Compara uma **região específica** de uma String com uma região de outra, sem precisar extrair substrings.

### Versão case-sensitive

java

```java
String banana = "banana";
String ana    = "ana";

boolean resultado = banana.regionMatches(1, ana, 0, 3);
// true — "ana" (índices 1-3 de "banana") == "ana" (índices 0-2 de "ana")
```

### Assinatura

java

```java
str.regionMatches(int toffset, String other, int ooffset, int len)
```

|Parâmetro|Significado|Valor no exemplo|
|---|---|---|
|`toffset`|Índice inicial em `this` (String base)|`1` → começa em `'a'` de `"banana"`|
|`other`|String a comparar|`"ana"`|
|`ooffset`|Índice inicial em `other`|`0` → começa em `'a'` de `"ana"`|
|`len`|Quantidade de caracteres a comparar|`3` → compara 3 chars|

```
"banana":  b(0)  a(1)  n(2)  a(3)  n(4)  a(5)
                 ↑_____________↑
                toffset=1    len=3  →  "ana"

"ana":     a(0)  n(1)  a(2)
           ↑_____________↑
          ooffset=0   len=3  →  "ana"

"ana" == "ana"  →  true ✅
```

### Versão ignorando maiúsculas/minúsculas

java

```java
String banana = "banana";
String ana    = "Ana";    // 'A' maiúsculo

// Sem ignoreCase → false (case-sensitive por padrão)
banana.regionMatches(1, ana, 0, 3);       // false — 'a' ≠ 'A'

// Com ignoreCase = true → true
banana.regionMatches(true, 1, ana, 0, 3); // true  — ignora maiúsculas
```

### Assinatura com `ignoreCase`

java

```java
str.regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)
```

O parâmetro `ignoreCase` vem **antes** dos demais. Quando `true`, funciona como um `equalsIgnoreCase` para a região especificada.

---

## 5️⃣ `startsWith()` e `endsWith()` — Prefixo e Sufixo

java

```java
String banana = "banana";
String ana    = "ana";

System.out.println(banana.endsWith(ana));   // true  — "banana" termina com "ana"
System.out.println(banana.startsWith(ana)); // false — "banana" não começa com "ana"
System.out.println(banana.startsWith("ban")); // true
```

Ambos são **case-sensitive**:

java

```java
"Banana".startsWith("ban")  // false — 'B' ≠ 'b'
"Banana".startsWith("Ban")  // true
```

> **Uso prático:** Verificar extensões de arquivo, prefixos de URL, validação de formato.

java

```java
// Verificar se é um arquivo Java
if (nomeArquivo.endsWith(".java")) { ... }

// Verificar se é uma URL HTTPS
if (url.startsWith("https://")) { ... }
```

---

## 6️⃣ `compareTo()` — Comparação Lexicográfica (Ordem Alfabética)

`compareTo()` não retorna `true` ou `false` — retorna um **`int`** que indica a relação de ordem entre as duas Strings com base nos valores ASCII de seus caracteres.

java

```java
String a          = "a";
String b          = "b";
String aMaiusculo = "A";

System.out.println(a.compareTo(b));          // negativo (-1) → "a" vem antes de "b"
System.out.println(b.compareTo(a));          // positivo (+1) → "b" vem depois de "a"
System.out.println(a.compareTo(a));          // 0             → iguais
System.out.println(a.compareTo(aMaiusculo)); // positivo (+32) → 'a'(97) - 'A'(65) = 32
```

### Regra de interpretação do retorno

|Retorno|Significado|
|---|---|
|`< 0` (negativo)|`this` vem **antes** de `other` na ordem ASCII|
|`= 0`|As Strings são **iguais**|
|`> 0` (positivo)|`this` vem **depois** de `other` na ordem ASCII|

### Como funciona internamente

`compareTo()` subtrai os valores ASCII dos caracteres na primeira posição divergente:

```
"a" (ASCII 97) compareTo "b" (ASCII 98)  →  97 - 98 = -1  (negativo → "a" < "b")
"b" (ASCII 98) compareTo "a" (ASCII 97)  →  98 - 97 = +1  (positivo → "b" > "a")
"a" (ASCII 97) compareTo "A" (ASCII 65)  →  97 - 65 = +32 (positivo → minúscula > maiúscula)
```

### Uso prático — Ordenação de listas

java

```java
List<String> nomes = new ArrayList<>(List.of("Carlos", "Ana", "Bruno"));

// Ordenação crescente (A → Z) usando compareTo internamente
Collections.sort(nomes);
System.out.println(nomes); // → [Ana, Bruno, Carlos]

// Ordenação decrescente (Z → A)
nomes.sort((n1, n2) -> n2.compareTo(n1));
System.out.println(nomes); // → [Carlos, Bruno, Ana]

// Ignorando maiúsculas/minúsculas
nomes.sort(String::compareToIgnoreCase);
```

> **`compareTo()` implementa a interface `Comparable<String>`**, o que permite que Strings sejam usadas em `TreeSet`, `TreeMap` e qualquer estrutura que dependa de ordem natural.

---

## 📊 Comparativo Final — Quando Usar Cada Método

|Situação|Método recomendado|
|---|---|
|Comparar conteúdo exato|`equals()`|
|Comparar ignorando maiúsculas|`equalsIgnoreCase()`|
|Verificar se começa com algo|`startsWith()`|
|Verificar se termina com algo|`endsWith()`|
|Comparar trecho de duas Strings|`regionMatches()`|
|Ordenar / verificar qual vem antes|`compareTo()`|
|Ordenar sem distinção de caso|`compareToIgnoreCase()`|
|Verificar se é o mesmo objeto|`==` (uso raro)|


# 🔎 Java String — Busca em Strings

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## 🧭 Visão Geral

Java oferece três métodos principais para **localizar conteúdo dentro de uma String**:

|Método|Retorna|Busca por|
|---|---|---|
|`indexOf()`|`int` — índice da **primeira** ocorrência, ou `-1`|`char` ou `String`|
|`lastIndexOf()`|`int` — índice da **última** ocorrência, ou `-1`|`char` ou `String`|
|`contains()`|`boolean`|Sequência de caracteres (`CharSequence`)|

Todos são **case-sensitive** por padrão — `'A'` e `'a'` são tratados como caracteres distintos.

---

## 1️⃣ `indexOf()` — Primeira Ocorrência

Retorna o índice da **primeira vez** que o caractere ou String pesquisado aparece. Se não encontrar, retorna **`-1`**.

java

```java
String banana = "banana";
String ana    = "ana";

System.out.println(banana.indexOf('x'));   // -1 — 'x' não existe em "banana"
System.out.println(banana.indexOf('b'));   //  0 — 'b' está no índice 0
System.out.println(banana.indexOf('a'));   //  1 — primeiro 'a' está no índice 1
System.out.println(banana.indexOf(ana));   //  1 — "ana" começa no índice 1
```

### Mapa de índices de `"banana"`

```
b  a  n  a  n  a
0  1  2  3  4  5

indexOf('b')   → 0  (único 'b')
indexOf('a')   → 1  (primeiro 'a')
indexOf("ana") → 1  ("ana" ocorre em [1,2,3])
indexOf('x')   → -1 (não encontrado)
```

### Sobrecargas do `indexOf()`

java

```java
// Buscar char
banana.indexOf('a');           // → 1  (primeira ocorrência)

// Buscar char a partir de um offset
banana.indexOf('a', 2);        // → 3  (ignora os índices 0 e 1, busca a partir de 2)

// Buscar String
banana.indexOf("ana");         // → 1

// Buscar String a partir de um offset
banana.indexOf("ana", 2);      // → 3  (encontra a segunda "ana" em [3,4,5])
```

> **Uso prático do `offset`:** Útil para encontrar **todas as ocorrências** de um padrão em um loop.

java

```java
// ✅ Encontrar todas as posições de 'a' em "banana"
String texto = "banana";
int pos = texto.indexOf('a');

while (pos != -1) {
    System.out.println("'a' encontrado no índice: " + pos);
    pos = texto.indexOf('a', pos + 1); // busca a partir da próxima posição
}
// Output:
// 'a' encontrado no índice: 1
// 'a' encontrado no índice: 3
// 'a' encontrado no índice: 5
```

> ⚠️ **Padrão importante:** Sempre verificar se o retorno é `-1` antes de usar o índice — usá-lo diretamente sem checar pode causar lógica incorreta ou `StringIndexOutOfBoundsException`.

java

```java
int indice = texto.indexOf("xyz");
if (indice != -1) {
    // seguro — o padrão foi encontrado
    System.out.println("Encontrado em: " + indice);
} else {
    System.out.println("Padrão não encontrado");
}
```

---

## 2️⃣ `lastIndexOf()` — Última Ocorrência

Funciona exatamente como `indexOf()`, mas retorna o índice da **última ocorrência** — ou seja, varre a String da direita para a esquerda.

java

```java
String banana = "banana";

System.out.println(banana.lastIndexOf('a')); // 5 — último 'a' está no índice 5
System.out.println(banana.lastIndexOf('n')); // 4 — último 'n' está no índice 4
System.out.println(banana.lastIndexOf('b')); // 0 — só existe um 'b', no índice 0
System.out.println(banana.lastIndexOf('x')); // -1 — não encontrado
```

```
b  a  n  a  n  a
0  1  2  3  4  5
               ↑
         lastIndexOf('a') → 5
```

### Sobrecargas do `lastIndexOf()`

java

```java
// Buscar char
banana.lastIndexOf('a');        // → 5

// Buscar char até um limite máximo (busca da direita até o offset)
banana.lastIndexOf('a', 4);     // → 3  (ignora índices maiores que 4)

// Buscar String
banana.lastIndexOf("an");       // → 3  (última ocorrência de "an" começa em 3)
```

> **Uso prático:** Extrair extensão de arquivo ou caminho final de uma URL.

java

```java
String caminho = "/home/usuario/documentos/relatorio.pdf";

int ultimaBarra = caminho.lastIndexOf('/');
String nomeArquivo = caminho.substring(ultimaBarra + 1);
// → "relatorio.pdf"

int ultimoPonto = nomeArquivo.lastIndexOf('.');
String extensao = nomeArquivo.substring(ultimoPonto + 1);
// → "pdf"
```

---

## 3️⃣ `contains()` — Verificar Presença de Substring

Retorna `true` se a String contém a sequência especificada, `false` caso contrário. Não informa a posição — apenas a presença.

java

```java
String banana = "banana";
String ana    = "ana";

System.out.println(banana.contains(ana));    // true  — "ana" existe em "banana"
System.out.println(banana.contains("xyz"));  // false — "xyz" não existe
System.out.println(banana.contains(""));     // true  — String vazia está em qualquer String
```

### `contains()` vs `indexOf()`

||`contains()`|`indexOf()`|
|---|---|---|
|**Retorno**|`boolean`|`int` (índice ou -1)|
|**Uso**|Apenas saber SE existe|Saber SE existe E ONDE|
|**Legibilidade**|Mais expressivo para `if`|Necessário quando a posição importa|

java

```java
// ✅ Quando só importa SE existe — contains() é mais legível
if (banana.contains("ana")) {
    System.out.println("Contém 'ana'");
}

// ✅ Quando a posição importa — use indexOf()
int pos = banana.indexOf("ana");
if (pos != -1) {
    System.out.println("'ana' encontrado na posição " + pos);
}
```

> ⚠️ **Detalhe técnico:** `contains()` recebe um `CharSequence` — interface implementada por `String`, `StringBuilder` e `StringBuffer`. Isso significa que você pode passar qualquer um desses tipos como argumento.

java

```java
StringBuilder sb = new StringBuilder("ana");
System.out.println(banana.contains(sb)); // true — StringBuilder também funciona
```

---

## 📊 Comparativo Completo

|Método|Busca por|Retorna|Encontrou?|Não encontrou?|
|---|---|---|---|---|
|`indexOf(char)`|1 char|`int`|Índice (≥ 0)|`-1`|
|`indexOf(String)`|substring|`int`|Índice (≥ 0)|`-1`|
|`indexOf(char, offset)`|1 char a partir de offset|`int`|Índice (≥ 0)|`-1`|
|`lastIndexOf(char)`|1 char (última vez)|`int`|Índice (≥ 0)|`-1`|
|`lastIndexOf(String)`|substring (última vez)|`int`|Índice (≥ 0)|`-1`|
|`contains(CharSequence)`|sequência|`boolean`|`true`|`false`|

---

## 💡 Boas Práticas

java

```java
// ✅ Sempre checar -1 antes de usar o índice retornado
int i = texto.indexOf("padrão");
if (i != -1) {
    String trecho = texto.substring(i);
}

// ✅ Usar contains() para condicionais simples — mais legível
if (email.contains("@")) { ... }

// ✅ Usar indexOf() com offset para iterar sobre múltiplas ocorrências
int pos = texto.indexOf('a');
while (pos != -1) {
    // processa a ocorrência
    pos = texto.indexOf('a', pos + 1);
}

// ✅ Usar lastIndexOf() para extrair sufixos (extensões, caminhos)
String ext = arquivo.substring(arquivo.lastIndexOf('.') + 1);

// ❌ Nunca usar indexOf() sem checar -1 como índice direto
String sub = texto.substring(texto.indexOf("xyz")); // StringIndexOutOfBoundsException se não existir
```

# ✂️ Java String — Modificando Strings

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## ⚠️ Lembrete — Strings são Imutáveis

> Relacionado: `[[Java-String-Construtores-e-Memoria]]`

Nenhum dos métodos abaixo **modifica** a String original — todos retornam uma **nova String** com o resultado. Se você não atribuir o retorno a uma variável, a modificação é perdida.

java

```java
String texto = "  Olá  ";
texto.trim();                  // ❌ resultado ignorado — texto continua "  Olá  "
texto = texto.trim();          // ✅ resultado atribuído — texto agora é "Olá"
```

---

## 1️⃣ `substring()` — Extrair Trecho da String

Retorna uma nova String contendo apenas a parte especificada da original.

java

```java
String teste = "Isso e um teste";
//              0123456789...

System.out.println(teste.substring(10));     // → "teste"   (do índice 10 até o fim)
System.out.println(teste.substring(10, 15)); // → "teste"   (do índice 10 até 14)
System.out.println(teste.substring(0, 4));   // → "Isso"
```

### Mapa de índices de `"Isso e um teste"`

```
I  s  s  o     e     u  m     t  e  s  t  e
0  1  2  3  4  5  6  7  8  9  10 11 12 13 14
                                ↑____________↑
                           início=10      fim=15 (exclusivo)
```

### Assinaturas

java

```java
// Uma sobrecarga — do índice até o fim da String
str.substring(int beginIndex)

// Duas sobrecargas — do índice até o índice final (exclusivo)
str.substring(int beginIndex, int endIndex)
```

|Parâmetro|Significado|
|---|---|
|`beginIndex`|Índice inicial (inclusivo)|
|`endIndex`|Índice final (**exclusivo**) — o caractere nessa posição **não é incluído**|

> ⚠️ **`endIndex` é exclusivo** — `substring(10, 15)` retorna os caracteres nas posições `10, 11, 12, 13, 14`. A posição `15` não entra.

> ⚠️ **`StringIndexOutOfBoundsException`** é lançada se `beginIndex < 0`, `endIndex > length()`, ou `beginIndex > endIndex`.

### Uso prático

java

```java
String email = "usuario@empresa.com";

// Extrair usuário e domínio
int arroba = email.indexOf('@');
String usuario = email.substring(0, arroba);       // → "usuario"
String dominio = email.substring(arroba + 1);       // → "empresa.com"

// Extrair extensão de arquivo
String arquivo = "relatorio.pdf";
String extensao = arquivo.substring(arquivo.lastIndexOf('.') + 1); // → "pdf"
```

---

## 2️⃣ `concat()` — Concatenar Strings

java

```java
String ola   = "ola ";
String mundo = "Mundo";

String olaMundo = ola.concat(mundo); // → "ola Mundo"
```

`concat()` é funcionalmente equivalente ao operador `+` para Strings:

java

```java
ola.concat(mundo)  ==  ola + mundo  // resultado idêntico
```

### Diferenças internas entre `+`, `concat()` e `StringBuilder`

||`+`|`concat()`|`StringBuilder`|
|---|---|---|---|
|**Overhead**|Compilador pode usar `StringBuilder` internamente|Cria novo objeto `String` sempre|Nenhum — buffer mutável|
|**`null` no argumento**|Concatena a String `"null"`|⚠️ Lança `NullPointerException`|Concatena a String `"null"`|
|**Uso recomendado**|Expressões simples|Evitar — prefira `+` ou `StringBuilder`|Loops e múltiplas concatenações|

java

```java
String s = null;
"ola " + s          // → "ola null"    (não quebra)
"ola ".concat(s)    // → NullPointerException ❌
```

> **Conclusão:** `concat()` não oferece vantagem real sobre `+`. Use `StringBuilder` para múltiplas concatenações.  
> Relacionado: `[[Java-String-Concatenacao-e-Imutabilidade]]`

---

## 3️⃣ `replace()` e `replaceAll()` — Substituir Conteúdo

### `replace()` — Substitui **todas** as ocorrências de um char ou CharSequence

java

```java
String espaco = "i s p a c o";

// Troca o char 'i' pelo char 'e'
String comE = espaco.replace('i', 'e');
// → "e s p a c o"

// Remove todos os espaços (substitui ' ' por "")
String semEspaco = comE.replaceAll(" ", "");
// → "espaco"
```

> ⚠️ **Correção importante no código original:**  
> O comentário dizia que `replace()` "só troca uma instância" — isso está **incorreto**.  
> `replace(char, char)` e `replace(CharSequence, CharSequence)` substituem **todas** as ocorrências.  
> A diferença real está em `replaceAll()`, que aceita **expressões regulares (Regex)** como primeiro parâmetro.

### Comparativo: `replace()` vs `replaceAll()` vs `replaceFirst()`

|Método|Primeiro parâmetro|Substitui|
|---|---|---|
|`replace(char, char)`|Literal `char`|Todas as ocorrências|
|`replace(CharSequence, CharSequence)`|Literal `String`|Todas as ocorrências|
|`replaceAll(String regex, String)`|**Regex**|Todas as ocorrências que casam com o padrão|
|`replaceFirst(String regex, String)`|**Regex**|Apenas a **primeira** ocorrência|

java

```java
String texto = "banana";

texto.replace('a', 'o');              // → "bonono"        (todas as ocorrências)
texto.replaceAll("[aeiou]", "*");     // → "b*n*n*"        (regex — todas as vogais)
texto.replaceFirst("[aeiou]", "*");   // → "b*nana"        (regex — só a primeira vogal)
```

### Exemplos práticos com Regex no `replaceAll()`

java

```java
String cpf = "123.456.789-09";

// Remover tudo que não for número
String apenasNumeros = cpf.replaceAll("[^0-9]", "");
// → "12345678909"

// Remover espaços extras entre palavras
String texto = "Java   é   incrível";
String limpo = texto.replaceAll("\\s+", " ");
// → "Java é incrível"

// Remover caracteres especiais
String entrada = "usuário@#nome!";
String seguro = entrada.replaceAll("[^a-zA-Z0-9]", "");
// → "usuárionome"
```

> **Relacionado:** `[[Java Regex — Expressões Regulares]]`

---

## 4️⃣ `trim()` e `strip()` — Remover Espaços nas Extremidades

java

```java
String nome = "   meu nome e:   ";

System.out.println(nome.trim());  // → "meu nome e:"  (remove espaços do início e do fim)
```

`trim()` remove os caracteres com código ASCII ≤ 32 (espaço, tab, newline) das extremidades da String — o conteúdo do meio **não é alterado**.

### `trim()` vs `strip()` — Qual usar?

A partir do **Java 11**, `strip()` é a forma recomendada:

|Método|Remove|Suporte a Unicode?|Versão|
|---|---|---|---|
|`trim()`|Caracteres ASCII ≤ 32|❌ Não|Java 1.0|
|`strip()`|Whitespace Unicode|✅ Sim|Java 11+|
|`stripLeading()`|Whitespace só do início|✅ Sim|Java 11+|
|`stripTrailing()`|Whitespace só do fim|✅ Sim|Java 11+|

java

```java
String nome = "   João   ";

nome.trim()          // → "João"
nome.strip()         // → "João"  (preferível — suporte Unicode completo)
nome.stripLeading()  // → "João   " (remove só do início)
nome.stripTrailing() // → "   João" (remove só do fim)
```

---

## 🌐 Contexto de Uso — WebServices e Integração de Sistemas

Como mencionado na aula, esses métodos de modificação são amplamente usados em **integração entre sistemas**:

**Cenários comuns:**

|Cenário|Métodos utilizados|
|---|---|
|Leitura de **XML / JSON** via WebService|`trim()`, `replace()`, `substring()`|
|Processamento de **Flat Files** (arquivos posicionais)|`substring()` — extrai campos por posição|
|Limpeza de dados antes de salvar no banco|`trim()`, `replaceAll()`|
|Formatação de CPF, CNPJ, CEP|`replaceAll("[^0-9]", "")`|
|Parsing de CSV|`split(",")`, `trim()`|

**Exemplo — lendo campo posicional de um Flat File:**

java

```java
// Flat File: cada campo ocupa posições fixas na linha
// Posição 0-9: nome | Posição 10-19: CPF | Posição 20-29: data
String linha = "João Silva12345678901202501015";

String nome = linha.substring(0, 10).trim();  // → "João Silva"
String cpf  = linha.substring(10, 21).trim(); // → "12345678901"
String data = linha.substring(21, 29).trim(); // → "20250101"
```

**Exemplo — limpando dados de entrada JSON/XML:**

java

```java
String valorBruto = "  R$ 1.500,00  ";

String valorLimpo = valorBruto
    .trim()                        // remove espaços das extremidades → "R$ 1.500,00"
    .replace("R$ ", "")           // remove o prefixo             → "1.500,00"
    .replace(".", "")             // remove separador de milhar    → "1500,00"
    .replace(",", ".");            // troca vírgula por ponto       → "1500.00"

double valor = Double.parseDouble(valorLimpo); // → 1500.0
```

---

## 📊 Resumo dos Métodos

|Método|O que faz|Retorna|
|---|---|---|
|`substring(begin)`|Trecho do índice `begin` até o fim|`String`|
|`substring(begin, end)`|Trecho de `begin` até `end` (exclusivo)|`String`|
|`concat(str)`|Junta duas Strings (equivalente ao `+`)|`String`|
|`replace(old, new)`|Substitui **todas** as ocorrências (literal)|`String`|
|`replaceAll(regex, new)`|Substitui **todas** as ocorrências (regex)|`String`|
|`replaceFirst(regex, new)`|Substitui apenas a **primeira** ocorrência (regex)|`String`|
|`trim()`|Remove espaços ASCII das extremidades|`String`|
|`strip()`|Remove whitespace Unicode das extremidades (Java 11+)|`String`|
|`stripLeading()`|Remove whitespace só do início (Java 11+)|`String`|
|`stripTrailing()`|Remove whitespace só do fim (Java 11+)|`String`|


# 🔡 Java String — Case, Join e Split

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## 1️⃣ `toLowerCase()` e `toUpperCase()` — Converter Caixa

java

```java
String teste = "Isso E UM TEste";

String testeMinuscula = teste.toLowerCase(); // → "isso e um teste"
String testeMaiuscula = teste.toUpperCase(); // → "ISSO E UM TESTE"
```

Ambos percorrem a String caractere por caractere e aplicam a conversão. A String original não é alterada (imutabilidade).

### Versões com `Locale` — Cuidado com internacionalização

java

```java
// Sem Locale — usa o padrão do sistema operacional (pode variar)
"TITLE".toLowerCase();

// Com Locale — comportamento previsível em qualquer ambiente
"TITLE".toLowerCase(Locale.forLanguageTag("pt-BR")); // → "title"
"title".toUpperCase(Locale.ENGLISH);                 // → "TITLE"
```

> ⚠️ **Por que isso importa?** Em alguns idiomas, a conversão de caixa tem regras diferentes. O caso mais famoso é o turco: `"I".toLowerCase()` em um sistema configurado com locale turco retorna `"ı"` (i sem ponto), não `"i"`. Em sistemas de produção que rodam em múltiplos países, sempre passe o `Locale` explicitamente.

### Uso prático

java

```java
// Comparação sem distinção de caixa (alternativa a equalsIgnoreCase)
if (entrada.toLowerCase().equals("sim")) { ... }

// Normalizar dado antes de salvar no banco
String emailNormalizado = email.trim().toLowerCase();
// "  Usuario@Empresa.COM  " → "usuario@empresa.com"

// Formatar exibição
String titulo = palavra.substring(0, 1).toUpperCase()
              + palavra.substring(1).toLowerCase();
// "jAVA" → "Java"
```

---

## 2️⃣ `String.join()` — Juntar Elementos com Delimitador

java

```java
String alfabeto = String.join(", ", "A", "B", "C");
// → "A, B, C"
```

> ⚠️ **Correção no código original:** O resultado esperado seria `"A, B, C"` — não `"A, B, C,"` com vírgula no final. `join()` coloca o delimitador **entre** os elementos, nunca após o último.

### Assinatura

java

```java
String.join(CharSequence delimiter, CharSequence... elements)
String.join(CharSequence delimiter, Iterable<? extends CharSequence> elements)
```

|Parâmetro|Significado|Valor no exemplo|
|---|---|---|
|`delimiter`|Separador inserido **entre** cada elemento|`", "`|
|`elements`|Elementos a unir (varargs ou `List`)|`"A"`, `"B"`, `"C"`|

### Com List — uso muito comum

java

```java
List<String> nomes = List.of("Ana", "Bruno", "Carlos");

String resultado = String.join(" | ", nomes);
// → "Ana | Bruno | Carlos"

String csv = String.join(",", nomes);
// → "Ana,Bruno,Carlos"
```

### `join()` vs `StringBuilder` com loop

java

```java
// ✅ String.join() — conciso e legível para coleções prontas
String resultado = String.join(", ", lista);

// ✅ StringBuilder — mais flexível quando precisa filtrar ou transformar
StringBuilder sb = new StringBuilder();
for (String item : lista) {
    if (!item.isEmpty()) {
        if (sb.length() > 0) sb.append(", ");
        sb.append(item);
    }
}

// ✅ Java 8+ — Collectors.joining() em Streams
String resultado = lista.stream()
    .filter(s -> !s.isEmpty())
    .collect(Collectors.joining(", "));
```

---

## 3️⃣ `split()` — Separar String em Array

É o inverso do `join()` — divide uma String em um array de Strings usando um **delimitador como Regex**.

java

```java
String alfabeto = String.join(", ", "A", "B", "C"); // → "A, B, C"
String[] letras = alfabeto.split(", ");              // → ["A", "B", "C"]

System.out.println(letras[0]); // → "A"
System.out.println(letras[1]); // → "B"
System.out.println(letras[2]); // → "C"
```

### Assinatura

java

```java
str.split(String regex)
str.split(String regex, int limit)
```

|Parâmetro|Significado|
|---|---|
|`regex`|Delimitador (aceita expressões regulares)|
|`limit`|Limite de partes geradas (opcional) — `0` = sem limite, `n` = máximo `n` partes|

> ⚠️ **`split()` aceita Regex** — certos caracteres têm significado especial e precisam ser escapados:

java

```java
// ❌ Erro — '.' em Regex significa "qualquer caractere"
"a.b.c".split(".");   // → [] (array vazio — inesperado)

// ✅ Correto — escapar o ponto com \\
"a.b.c".split("\\.");  // → ["a", "b", "c"]

// Outros caracteres que precisam de escape: | + * ? ( ) [ ] { } ^ $ \
"a|b|c".split("\\|");  // → ["a", "b", "c"]
```

### `split()` com `limit`

java

```java
String linha = "1;Antonio;30;SP;Brasil";

// Sem limit — divide em todas as partes
String[] tudo = linha.split(";");
// → ["1", "Antonio", "30", "SP", "Brasil"]

// Com limit = 3 — divide em no máximo 3 partes
String[] parcial = linha.split(";", 3);
// → ["1", "Antonio", "30;SP;Brasil"]  (o restante fica junto na última parte)
```

---

## 4️⃣ Exemplo Real — Parsing de Flat File com `split()`

O `split()` é um dos principais recursos para leitura de **arquivos CSV** e **Flat Files** — formatos amplamente usados em integração entre sistemas (WebServices, ETL, bancos legados).

java

```java
public class ExemploSplit {
    public static void main(String[] args) {

        // Linha de um Flat File delimitado por ';'
        // Formato: id;nome;idade
        String linhaArquivo = "1;Antonio;30";

        String[] campos = linhaArquivo.split(";");
        //           campos[0] = "1"
        //           campos[1] = "Antonio"
        //           campos[2] = "30"

        int    id    = Integer.parseInt(campos[0]); // String → int
        String nome  = campos[1];
        int    idade = Integer.parseInt(campos[2]); // String → int

        Pessoa funcionario = new Pessoa(id, nome, idade);
    }
}
```

> **`Integer.parseInt()`** converte uma `String` numérica para `int`. Se a String não for um número válido, lança `NumberFormatException`.

java

```java
Integer.parseInt("30")   // → 30      ✅
Integer.parseInt("abc")  // → NumberFormatException ❌
Integer.parseInt(" 30")  // → NumberFormatException ❌ (espaço causa erro — use trim() antes)

// ✅ Boa prática: trim() antes de parseInt()
int idade = Integer.parseInt(campos[2].trim());
```

### Lendo múltiplas linhas de um arquivo CSV

java

```java
// Simulando leitura de um arquivo CSV com cabeçalho
String csv = """
        id;nome;idade
        1;Antonio;30
        2;Maria;25
        3;Carlos;40
        """;

String[] linhas = csv.split("\n");  // divide por quebra de linha

List<Pessoa> pessoas = new ArrayList<>();

for (int i = 1; i < linhas.length; i++) {  // começa em 1 para pular o cabeçalho
    String linha = linhas[i].trim();
    if (linha.isEmpty()) continue;          // ignora linhas em branco

    String[] campos = linha.split(";");
    int    id    = Integer.parseInt(campos[0].trim());
    String nome  = campos[1].trim();
    int    idade = Integer.parseInt(campos[2].trim());

    pessoas.add(new Pessoa(id, nome, idade));
}
```

### Contextos de uso — Integração de Sistemas

|Formato|Delimitador comum|Exemplo de uso|
|---|---|---|
|**CSV**|`,` ou `;`|Exportação de planilhas, relatórios|
|**TSV**|`\t` (tab)|Dados de banco de dados|
|**Flat File posicional**|`substring()` (sem delimitador)|Sistemas bancários, governo|
|**JSON / XML**|Parseado com bibliotecas|WebServices REST / SOAP|
|**Log files**|Espaço, `\|`, `:`|Análise de logs de servidor|

---

## 📊 Resumo dos Métodos

|Método|O que faz|Retorna|
|---|---|---|
|`toLowerCase()`|Converte toda a String para minúsculas|`String`|
|`toUpperCase()`|Converte toda a String para maiúsculas|`String`|
|`toLowerCase(Locale)`|Conversão com locale explícito|`String`|
|`String.join(delim, ...)`|Une elementos com delimitador entre eles|`String`|
|`split(regex)`|Divide String em array pelo padrão regex|`String[]`|
|`split(regex, limit)`|Divide em no máximo `limit` partes|`String[]`|
|`Integer.parseInt(str)`|Converte String numérica para `int`|`int`|


# 🏗️ Java — StringBuilder e StringBuffer

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## 🧭 Por que existem?

Como visto em `[[Java-String-Concatenacao-e-Imutabilidade]]`, Strings em Java são **imutáveis** — cada concatenação com `+` ou `+=` cria um **novo objeto** na memória e descarta o anterior.

java

```java
// ❌ MÁ PRÁTICA — cria N objetos String descartados
String resultado = "";
for (String letra : letras) {
    resultado += letra; // novo objeto a cada iteração → Garbage Collector sobrecarregado
}
```

`StringBuilder` e `StringBuffer` resolvem isso com um **buffer mutável interno** — um array de `char` que cresce conforme necessário, sem criar objetos intermediários.

```
String com +=:       "A" → "AB" → "ABC" → "ABCD"   (4 objetos criados, 3 descartados)
StringBuilder:       [A | B | C | D | _ | _ ]         (1 objeto, buffer expandido)
```

---

## 1️⃣ `StringBuffer` — Concatenação Thread-Safe

java

```java
public class StringBufferAppend {
    public static void main(String[] args) {
        String[] letras = {"A", "B", "C", "D", "E"};

        StringBuffer sb = new StringBuffer(); // buffer vazio, capacidade inicial de 16 chars

        for (String letra : letras) {
            sb.append(letra); // adiciona ao buffer sem criar novo objeto
        }

        String resultado = sb.toString(); // converte para String imutável ao final
        System.out.println(resultado);    // → "ABCDE"
    }
}
```

### `reverse()` — Inverter o conteúdo do buffer

> ⚠️ **Correção no código original:** `reverse()` é um método do `StringBuffer`/`StringBuilder`, não da classe `String`. O código tentava chamar `teste.reverse()` em uma `String`, o que não existe e causaria erro de compilação. O correto é:

java

```java
public class StringBufferReverse {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("teste");

        String aoContrario = sb.reverse().toString();
        System.out.println(aoContrario); // → "etset"
    }
}
```

---

## 2️⃣ `StringBuilder` — Concatenação de Alta Performance

Funciona de forma idêntica ao `StringBuffer` em termos de API — os métodos são os mesmos. A diferença está apenas no comportamento em ambientes multi-thread.

java

```java
public class StringBuilderExemplo {
    public static void main(String[] args) {
        String[] letras = {"A", "B", "C", "D", "E"};

        StringBuilder sb = new StringBuilder();

        for (String letra : letras) {
            sb.append(letra);
        }

        System.out.println(sb.toString()); // → "ABCDE"
    }
}
```

---

## ⚡ Diferença Central — Thread Safety

||`StringBuilder`|`StringBuffer`|
|---|---|---|
|**Thread-safe**|❌ Não|✅ Sim|
|**Performance**|✅ Mais rápido|⚠️ Mais lento|
|**Como funciona**|Sem sincronização|Métodos `synchronized`|
|**Quando usar**|Single-thread (uso geral)|Multi-thread (instância compartilhada entre threads)|

### Por que `StringBuffer` é mais lento?

Cada método do `StringBuffer` é marcado com `synchronized` — isso significa que apenas **uma thread por vez** pode executá-lo. Essa garantia tem um custo de performance que não faz sentido pagar quando não há concorrência.

java

```java
// Internamente no StringBuffer (simplificado):
public synchronized StringBuffer append(String str) {
    // só uma thread executa isto por vez
    super.append(str);
    return this;
}

// Internamente no StringBuilder (simplificado):
public StringBuilder append(String str) {
    // sem synchronized — qualquer thread pode executar ao mesmo tempo
    super.append(str);
    return this;
}
```

> **Relacionado:** `[[Java-Threads-Deadlocks-e-Semaforo]]` — `synchronized` e Thread Safety

### Quando usar `StringBuffer` na prática?

Somente quando o **mesmo objeto** `StringBuffer` for acessado por **múltiplas threads simultaneamente**. Na maioria dos casos, variáveis locais de método já são isoladas por thread naturalmente, tornando `StringBuffer` desnecessário mesmo em código multi-thread.

java

```java
// ✅ StringBuilder é suficiente aqui — sb é variável LOCAL, não compartilhada entre threads
public String montarRelatorio(List<String> linhas) {
    StringBuilder sb = new StringBuilder(); // cada thread tem o seu próprio sb
    for (String linha : linhas) {
        sb.append(linha).append("\n");
    }
    return sb.toString();
}

// ⚠️ StringBuffer necessário aqui — sb é campo de instância compartilhado entre threads
public class Logger {
    private StringBuffer log = new StringBuffer(); // compartilhado → thread-safe necessário

    public void registrar(String mensagem) {
        log.append(mensagem).append("\n"); // múltiplas threads podem chamar isso
    }
}
```

---

## 🛠️ Métodos Principais (Comuns a Ambos)

java

```java
StringBuilder sb = new StringBuilder("Java");

// append — adiciona ao final (aceita String, int, char, boolean, double, Object...)
sb.append(" é");
sb.append(" incrível");
System.out.println(sb); // → "Java é incrível"

// insert — insere em uma posição específica
sb.insert(4, " ainda");
System.out.println(sb); // → "Java ainda é incrível"

// delete — remove do índice begin (inclusivo) até end (exclusivo)
sb.delete(5, 11);
System.out.println(sb); // → "Java é incrível"

// replace — substitui trecho
sb.replace(0, 4, "Python");
System.out.println(sb); // → "Python é incrível"

// reverse — inverte o buffer
sb.reverse();
System.out.println(sb); // → "levírcni é nohtyP"

// charAt / indexOf / length — mesmos que String
System.out.println(sb.length());    // tamanho atual do buffer
System.out.println(sb.charAt(0));   // primeiro caractere
System.out.println(sb.indexOf("é")); // posição do 'é'

// toString — converte para String imutável
String resultado = sb.toString();

// deleteCharAt — remove um único caractere
sb.deleteCharAt(0);

// setCharAt — substitui um único caractere
sb.setCharAt(0, 'X');
```

### Method Chaining — Encadeamento de chamadas

Como cada método retorna o próprio `StringBuilder`/`StringBuffer`, é possível encadear chamadas:

java

```java
String resultado = new StringBuilder()
    .append("Olá, ")
    .append("mundo")
    .append("!")
    .reverse()
    .toString();
// → "!odnum ,álO"
```

---

## 📊 Comparativo Final — String vs StringBuilder vs StringBuffer

||`String`|`StringBuilder`|`StringBuffer`|
|---|---|---|---|
|**Mutável**|❌ Não|✅ Sim|✅ Sim|
|**Thread-safe**|✅ Sim (imutável)|❌ Não|✅ Sim|
|**Performance**|❌ Ruim em loops|✅ Melhor|⚠️ Boa, com overhead|
|**Uso recomendado**|Valores fixos, literais|Concatenação em single-thread|Concatenação em multi-thread compartilhada|
|**`reverse()`**|❌ Não existe|✅ Sim|✅ Sim|
|**`append()`**|❌ Não existe|✅ Sim|✅ Sim|

---

## 💡 Guia Rápido — O que usar?

```
Você está concatenando Strings?
│
├── NÃO → use String normalmente
│
└── SIM
    ├── Poucas concatenações (2-3) fora de loop?
    │       └── String com + é aceitável ✅
    │
    ├── Muitas concatenações ou dentro de loop?
    │   │
    │   ├── Single-thread ou variável local?
    │   │       └── StringBuilder ✅ (mais rápido)
    │   │
    │   └── Multi-thread com instância compartilhada?
    │           └── StringBuffer ✅ (thread-safe)
    │
    └── Java 8+ com Stream?
            └── Collectors.joining(", ") ✅
```


# 🔤 Java — StringTokenizer

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Strings]]

---

## 🧭 O que é um Token?

Um **token** é um pedaço de texto extraído de uma String maior com base em um **delimitador**. O `StringTokenizer` percorre a String e entrega um token por vez, sem precisar criar um array completo de antemão — o que pode ser mais eficiente para arquivos muito grandes.

```
String:  "joao;1;34"
Tokens:   "joao"  →  "1"  →  "34"
```

---

## 🏗️ Construtores do `StringTokenizer`

java

```java
// Construtor 1 — delimitador padrão: espaço, tab, newline, form feed
StringTokenizer st = new StringTokenizer("joao 1 34");

// Construtor 2 — delimitador customizado (mais comum)
StringTokenizer st = new StringTokenizer("joao;1;34", ";");

// Construtor 3 — delimitador customizado + retornar os próprios delimitadores como tokens
StringTokenizer st = new StringTokenizer("joao;1;34", ";", true);
// tokens: "joao", ";", "1", ";", "34"
```

---

## 1️⃣ Uso Básico

java

```java
import java.util.StringTokenizer;

public class ExemploStringTokenizer {
    public static void main(String[] args) {
        String arquivo = "joao;1;34";
        StringTokenizer st = new StringTokenizer(arquivo, ";");

        while (st.hasMoreTokens()) {            // enquanto houver tokens → true
            System.out.println(st.nextToken()); // lê e avança para o próximo token
        }
    }
}
```

**Output:**

```
joao
1
34
```

### Como o loop funciona

```
Iteração 1: hasMoreTokens() → true  | nextToken() → "joao"
Iteração 2: hasMoreTokens() → true  | nextToken() → "1"
Iteração 3: hasMoreTokens() → true  | nextToken() → "34"
Iteração 4: hasMoreTokens() → false | loop encerra
```

> ⚠️ **Chamar `nextToken()` quando não há mais tokens lança `NoSuchElementException`**. O `while (st.hasMoreTokens())` previne isso — sempre cheque antes de consumir.

---

## 2️⃣ Métodos do `StringTokenizer`

|Método|Retorna|O que faz|
|---|---|---|
|`hasMoreTokens()`|`boolean`|`true` se ainda há tokens a consumir|
|`nextToken()`|`String`|Retorna o próximo token e avança o cursor|
|`nextToken(String delim)`|`String`|Muda o delimitador e retorna o próximo token|
|`countTokens()`|`int`|Quantidade de tokens restantes (sem consumir)|
|`hasMoreElements()`|`boolean`|Alias de `hasMoreTokens()` — implementa `Enumeration`|
|`nextElement()`|`Object`|Alias de `nextToken()` — implementa `Enumeration`|

java

```java
StringTokenizer st = new StringTokenizer("joao;1;34", ";");

System.out.println(st.countTokens()); // → 3 (sem consumir nenhum)
System.out.println(st.nextToken());   // → "joao"
System.out.println(st.countTokens()); // → 2 (um foi consumido)
```

---

## 3️⃣ Exemplo Real — Lendo Flat File e Criando Objeto

java

```java
import java.util.StringTokenizer;

public class LeituraFlatFile {
    public static void main(String[] args) {
        // Linha de um arquivo delimitado por ';'
        // Formato: nome;id;idade
        String linha = "Antonio;1;30";

        StringTokenizer st = new StringTokenizer(linha, ";");

        String nome  = st.nextToken();                    // → "Antonio"
        int    id    = Integer.parseInt(st.nextToken());  // → 1
        int    idade = Integer.parseInt(st.nextToken());  // → 30

        Pessoa funcionario = new Pessoa(id, nome, idade);
        System.out.println("Criado: " + funcionario);
    }
}
```

### Lendo múltiplas linhas

java

```java
String arquivo = """
        Antonio;1;30
        Maria;2;25
        Carlos;3;40
        """;

List<Pessoa> pessoas = new ArrayList<>();

// Divide o arquivo em linhas primeiro
StringTokenizer linhas = new StringTokenizer(arquivo, "\n");

while (linhas.hasMoreTokens()) {
    String linha = linhas.nextToken().trim();
    if (linha.isEmpty()) continue;

    // Para cada linha, tokeniza os campos
    StringTokenizer campos = new StringTokenizer(linha, ";");
    String nome  = campos.nextToken();
    int    id    = Integer.parseInt(campos.nextToken());
    int    idade = Integer.parseInt(campos.nextToken());

    pessoas.add(new Pessoa(id, nome, idade));
}
```

---

## ⚖️ `StringTokenizer` vs `split()` — Quando usar cada um?

||`StringTokenizer`|`split()`|
|---|---|---|
|**Retorno**|Token por token (lazy)|Array completo de uma vez|
|**Aceita Regex**|❌ Não — apenas literal|✅ Sim|
|**Múltiplos delimitadores**|✅ Sim — qualquer char do conjunto|⚠️ Sim, mas via Regex|
|**Delimitadores consecutivos**|Ignora (trata como um só)|Gera tokens vazios `""`|
|**Performance (arquivos grandes)**|✅ Melhor (processa um token por vez)|⚠️ Cria todo o array antes|
|**Parte da API moderna**|⚠️ Legada (desde Java 1.0)|✅ Recomendada atualmente|

### Comportamento com delimitadores consecutivos

java

```java
String dado = "joao;;34"; // campo vazio entre os ;;

// split() — preserva o campo vazio como token ""
String[] partes = dado.split(";");
// → ["joao", "", "34"]   — índice 1 é String vazia

// StringTokenizer — ignora delimitadores consecutivos
StringTokenizer st = new StringTokenizer(dado, ";");
// → "joao", "34"          — o campo vazio some!
```

> ⚠️ **Atenção crítica:** Se o seu arquivo puder ter **campos vazios**, use `split()` — o `StringTokenizer` os ignorará silenciosamente, desalinhando todos os campos seguintes.

### Múltiplos delimitadores com `StringTokenizer`

java

```java
// StringTokenizer trata cada char do segundo argumento como delimitador separado
StringTokenizer st = new StringTokenizer("joao;1,34:SP", ";,:");
// tokens: "joao", "1", "34", "SP"  — divide por ';' OU ',' OU ':'
```

---

## 📌 Quando usar `StringTokenizer` hoje em dia?

`StringTokenizer` é uma classe **legada** — existe desde o Java 1.0 e nunca foi removida, mas a documentação oficial recomenda `split()` ou `Scanner` para novos projetos.

**Ainda faz sentido usar quando:**

- Trabalhar com código legado que já usa `StringTokenizer`
- O arquivo é muito grande e você quer processar **um token por vez** sem carregar tudo na memória
- Precisa de múltiplos delimitadores sem escrever Regex

**Prefira `split()` quando:**

- Precisar de Regex como delimitador
- Os campos puderem ser vazios
- A legibilidade for mais importante que micro-otimizações

---

## 📊 Resumo dos Métodos

| Método                            | Retorna   | O que faz                                |
| --------------------------------- | --------- | ---------------------------------------- |
| `new StringTokenizer(str, delim)` | —         | Cria o tokenizer com delimitador         |
| `hasMoreTokens()`                 | `boolean` | Verifica se ainda há tokens              |
| `nextToken()`                     | `String`  | Consome e retorna o próximo token        |
| `countTokens()`                   | `int`     | Conta tokens restantes sem consumir      |
| `nextToken(String novoDelim)`     | `String`  | Muda delimitador e retorna próximo token |



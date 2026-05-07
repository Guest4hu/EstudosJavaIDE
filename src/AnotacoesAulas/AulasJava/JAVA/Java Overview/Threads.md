# 🧵 Java Threads — Stop, Resume e Suspend (Implementação Segura)

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
# 🔒 Java Threads — Deadlocks e Exercício Semáforo

> **Status:** `revisado` | **Curso:** Oracle Java Fundamentos | **Tópico:** [[Threads]] 

---

## 🧱 O que é um Deadlock?

**Deadlock** (ou _impasse_) é uma situação em que duas ou mais threads ficam bloqueadas **permanentemente**, cada uma esperando por um recurso que está sendo segurado pela outra — e nenhuma consegue avançar.

É um dos bugs mais perigosos em sistemas concorrentes: **não lança exceção, não gera erro visível** — o programa simplesmente trava silenciosamente.

### Condições para um Deadlock ocorrer

Para que um deadlock aconteça, as **quatro condições de Coffman** precisam ser satisfeitas simultaneamente:

| Condição            | O que significa                                                     |
| ------------------- | ------------------------------------------------------------------- |
| **Exclusão mútua**  | O recurso só pode ser usado por uma thread por vez (`synchronized`) |
| **Posse e espera**  | A thread segura um recurso enquanto espera por outro                |
| **Sem preempção**   | Nenhuma thread pode forçar outra a liberar um recurso               |
| **Espera circular** | Thread A espera por Thread B, que espera por Thread A               |
|                     |                                                                     |

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
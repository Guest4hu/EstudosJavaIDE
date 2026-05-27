
## 🧭 Visão Geral

A primeira aula cobre a história do Java, suas versões e a **JVM (Java Virtual Machine)**, implementada em C.

O Java foi criado para ser uma linguagem **Orientada a Objetos (POO)** e possui diferentes edições:

|Edição|Uso|
|---|---|
|**Java SE** _(Standard Edition)_|Uso geral|
|**Java EE** _(Enterprise Edition)_|Aplicações corporativas|
|**Java ME** _(Micro Edition)_|Dispositivos embarcados|

---

## 🧱 Classes e Objetos

> **Conceito central de #poo**  
> Relacionado: [[POO — Conceitos Gerais]]

- **Classe** → o "molde" — representação de algo ou conceito
- **Objeto** → o "produto do molde" — instância específica de uma classe

**Exemplo:** `Figo` e `Rex` são cachorros distintos, mas ambos são instâncias da mesma classe `Cachorro`.

```java
// Classe = molde
public class Cachorro {
    String nome;
    double peso;
}

// Objetos = instâncias
Cachorro figo = new Cachorro();
Cachorro rex  = new Cachorro();
```

---

## ✅ Benefícios de Utilizar POO

- **Reutilização** — atributos e métodos compartilhados entre hierarquias
- **Código genérico** — classes pai fornecem base aproveitada pelas filhas
- **Manutenção fácil** — alterar a classe pai reflete em todas as filhas
- **Organização** — código mais próximo do mundo real

---

## 🔁 Herança e Polimorfismo

> Relacionado: [[POO — Conceitos Gerais]] | Tags: #herança #polimorfismo

A classe pai `Animal` possui:

- **Atributos:** `nome`, `peso`
- **Métodos:** `comer()`, `respirar()`

As classes `Cachorro` e `Peixe` **herdam** esses membros, mas **sobrescrevem** os métodos conforme necessário.

```java
public class Animal {
    String nome;
    double peso;
    void comer()    { System.out.println("Comendo..."); }
    void respirar() { System.out.println("Respirando..."); }
}

public class Cachorro extends Animal {
    @Override
    void respirar() { System.out.println("Respirando pelo pulmão!"); }
    void correrAtrasDeCarros() { System.out.println("Au au!"); }
}

public class Peixe extends Animal {
    @Override
    void respirar() { System.out.println("Respirando pelas guelras!"); }
    void nadar() { System.out.println("Nadando..."); }
}
```

### Duas formas de Polimorfismo em Java

|Tipo|O que é|
|---|---|
|**Sobrescrita (Override)**|Classe filha reescreve o comportamento do método herdado|
|**Sobrecarga (Overload)**|Mesmo método com parâmetros diferentes na mesma classe|

> ⚠️ **Polimorfismo** vem do grego: _"muitas formas"_. A JVM decide em tempo de execução qual método chamar com base na **classe real do objeto**.

---

## 🏗️ Arquiteturas Flexíveis — Interfaces, Enum e Generics

> Tags: #interfaces #enum #generics

|Recurso|Para que serve|
|---|---|
|**Interface**|Define um _contrato_ de métodos que uma classe deve implementar|
|**Enum**|Define constantes tipadas e seguras|
|**Generics**|Permite criar classes e métodos que funcionam com qualquer tipo|

```java
// Enum — constantes separadas por vírgula, ponto-e-vírgula apenas no último
public enum Feed {
    DOG_FOOD,
    FISH_FOOD;
}

// Lista genérica que só aceita implementações de Pet
List<Pet> pets = new ArrayList<>();
pets.add(new Dog());
pets.add(new Fish());
```

> ⚠️ **Correção:** Em Enum, as constantes são separadas por **vírgula**. Apenas o último item leva `;`.  
> A lista `List<Pet>` garante **segurança de tipos** em tempo de compilação.

---

## 📐 Análise, Design e Arquitetura — UML

> Relacionado: [[UML — Diagramas]] | Tag: #uml

**UML (Unified Modeling Language)** é um padrão internacional de modelagem visual para software.

**Usos principais:**

- Análise de requisitos empresariais
- Modelagem da estrutura de código
- Descrição do tipo de deploy da aplicação

**Diagramas mais comuns:**

|Diagrama|Finalidade|
|---|---|
|**Diagrama de Classes**|Estrutura do código — classes, atributos, métodos, relações|
|**Diagrama de Sequência**|Fluxo de chamadas entre objetos|
|**Diagrama de Casos de Uso**|Interação do usuário com o sistema|

---

## 📦 Java APIs

> Relacionado: [[Collections API]] · [[Streams API]] · [[Concurrency API]] · [[JPA e JDBC]]

| API                   | Para que serve                                           |
| --------------------- | -------------------------------------------------------- |
| **Collections**       | Gerenciar grupos de objetos (listas, filas, conjuntos)   |
| **Streams**           | Processar e filtrar grandes volumes de dados com lambdas |
| **IO / NIO**          | Ler e escrever em arquivos e outras fontes de dados      |
| **Concurrency**       | Executar tarefas em paralelo com múltiplas threads       |
| **Persistence (JPA)** | Conectar e persistir dados em banco de dados             |

---

### 🗂️ Collections API

> Tag: #collections | Relacionado: [[Collections API]]

Estruturas de dados prontas para gerenciar grupos de elementos:

|Estrutura|Característica principal|
|---|---|
|`ArrayList`|Lista baseada em array dinâmico — ótima para acesso por índice|
|`LinkedList`|Lista encadeada — ótima para inserções e remoções frequentes|
|`Set`|Não permite elementos duplicados (`HashSet`, `TreeSet`)|
|`Stack`|LIFO — _Last In, First Out_|
|`Queue`|FIFO — _First In, First Out_|

**Métodos comuns:**

```java
List<String> nomes = new ArrayList<>();
nomes.add("João");           // adiciona
nomes.remove("João");        // remove
nomes.contains("Maria");     // busca → false
nomes.set(0, "Pedro");       // atualiza índice 0
Collections.sort(nomes);     // ordena
```

> ⚠️ **Não existe método `update()`** na Collections API. Para atualizar, usa-se `set(index, novoValor)`.

---

### 🌊 Streams API

> Tag: #streams | Relacionado: [[Streams API]]

Eficiente para **filtrar, mapear e reduzir** grandes volumes de dados usando **Lambda Expressions**.

> **Lambda Expression** = função anônima, sem nome, sem tipo de retorno explícito e sem modificadores de acesso. Usada para implementar **interfaces funcionais** de forma concisa.

```java
List<Employee> employees = new ArrayList<>();

employees.stream()
         .parallel()                             // processa em paralelo (múltiplos núcleos)
         .filter(e -> e.getSalary() > 1000)      // filtra quem ganha mais de 1000
         .forEach(e -> e.calculateBonus());       // calcula bônus para cada filtrado
```

**Outros métodos úteis:** `.map()` · `.reduce()` · `.collect()` · `.sorted()` · `.distinct()`

> ⚠️ `.parallel()` é vantajoso para listas **muito grandes**. Para listas pequenas pode ser mais lento que o processamento sequencial.

---

### 📁 IO API (NIO)

> Tag: #io | Relacionado: [[Java IO e NIO]]

Focada em **leitura e escrita de dados** de diferentes fontes e destinos.

```java
Path file = Path.of("../employee.txt");          // define o caminho do arquivo
Files.lines(file, Charset.forName("UTF-8"))      // lê linha por linha em UTF-8
     .forEach(line -> System.out.println(line));  // imprime cada linha no terminal
```

> **Nota:** `Files.lines()` faz parte do pacote `java.nio.file` — versão moderna e eficiente da IO API.

---

### ⚙️ Concurrency API

> Tag: #concurrency | Relacionado: [[Concurrency API]]

Permite executar **múltiplas tarefas simultaneamente** (multi-threading), aproveitando os núcleos do processador.

```java
// Define uma tarefa que retorna um BigDecimal
Callable<BigDecimal> taxCalculation = new Callable<>() {
    public BigDecimal call() throws Exception {
        // Fazendo o cálculo de imposto...
        return tax;
    }
};

// Cria um pool de threads gerenciado automaticamente
ExecutorService es = Executors.newCachedThreadPool();

// Envia a tarefa e guarda referência do resultado futuro
Future<BigDecimal> result = es.submit(taxCalculation);

// Busca o resultado quando a tarefa terminar
BigDecimal valor = result.get();
```

**Conceitos essenciais:**

|Conceito|O que é|
|---|---|
|`Thread`|Unidade básica de execução paralela|
|`ExecutorService`|Gerencia um pool de threads|
|`Callable`|Tarefa que **retorna** um valor|
|`Runnable`|Tarefa que **não retorna** valor|
|`Future`|Referência ao resultado de uma tarefa assíncrona|
|`synchronized`|Evita _race condition_ entre threads|

---

### 🗄️ JPA e JDBC

> Tag: #jpa #jdbc | Relacionado: [[JPA e JDBC]]

**JDBC (Java Database Connectivity)** → protocolo de baixo nível para conectar Java a bancos SQL.

> ⚠️ Para usar JDBC é necessário ter um **driver JDBC** específico do banco (ex: MySQL Connector, PostgreSQL Driver).

**JPA** vai além — mapeia classes Java diretamente para tabelas do banco usando anotações:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;
}
```

**Diferença entre JDBC e JPA:**

||JDBC|JPA|
|---|---|---|
|**Nível**|Baixo nível|Alto nível|
|**Como funciona**|Queries SQL manuais|Mapeamento objeto-relacional (ORM)|
|**Exemplos**|`java.sql.*`|Hibernate, EclipseLink|

---

## 🔗 Mapa de Conexões

```
Java Fundamentos Aula 1
│
├── POO
│   ├── [[POO — Conceitos Gerais]]
│   ├── Herança → [[Herança e Polimorfismo]]
│   └── Interfaces → [[Interfaces e Contratos]]
│
├── JVM
│   └── [[JVM e Compilação]]
│
├── APIs
│   ├── [[Collections API]]
│   ├── [[Streams API]]
│   ├── [[Java IO e NIO]]
│   ├── [[Concurrency API]]
│   └── [[JPA e JDBC]]
│
└── Modelagem
    └── [[UML — Diagramas]]
```

---

_Anotações revisadas e complementadas em 25/04/2026_
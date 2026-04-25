# Oracle Java Fundamentos

> [!info]- 📅 Estudos 25/04/2026
> 
> > [!note]- 📖 Aula 1 — Introdução e História do Java
> > 
> > A primeira aula é mais teórica, cobrindo a história do Java, suas versões e a **JVM (Java Virtual Machine)**, implementada em C.
> > 
> > O Java foi criado para ser uma linguagem **Orientada a Objetos (POO)** e possui diferentes edições:
> > 
> > - **Java SE (Standard Edition)** → uso geral
> > - **Java EE (Enterprise Edition)** → aplicações corporativas
> > - **Java ME (Micro Edition)** → dispositivos embarcados
> 
> > [!note]- 🧱 Classes e Objetos
> > 
> > - **Classe** → representação de algo ou um conceito (o "molde")
> > - **Objeto** → uma instância específica de uma classe (o "produto do molde")
> > 
> > **Exemplo:** Figo e Rex são dois cachorros com suas particularidades, mas ambos são instâncias da mesma classe `Cachorro`.
> > 
> > ```java
> > // Classe = molde
> > public class Cachorro {
> >     String nome;
> >     double peso;
> > }
> > 
> > // Objetos = instâncias
> > Cachorro figo = new Cachorro();
> > Cachorro rex  = new Cachorro();
> > ```
> 
> > [!tip]- ✅ Benefícios de Utilizar POO
> > 
> > - Facilidade de **reutilizar atributos e métodos** entre hierarquias de classes
> > - Classes mais altas possuem **código genérico** aproveitado e adaptado pelas classes filhas
> > - **Manutenção mais fácil** — alterar a classe pai reflete em todas as filhas
> > - **Organização** — o código fica mais próximo do mundo real
> 
> > [!note]- 🔁 Herança e Polimorfismo
> > 
> > A classe pai `Animal` possui:
> > 
> > - **Atributos:** `nome`, `peso`
> > - **Métodos:** `comer()`, `respirar()`
> > 
> > As classes `Cachorro` e `Peixe` **herdam** esses atributos e métodos, mas **sobrescrevem** os métodos conforme necessário.
> > 
> > Essa capacidade do Java de executar o código com base na **classe real do objeto em tempo de execução** chama-se **Polimorfismo**.
> > 
> > Cada classe filha também pode ter **métodos exclusivos**:
> > 
> > - `Cachorro` → `correrAtrasDeCarros()`
> > - `Peixe` → `nadar()`
> > 
> > ```java
> > public class Animal {
> >     String nome;
> >     double peso;
> >     void comer()    { System.out.println("Comendo..."); }
> >     void respirar() { System.out.println("Respirando..."); }
> > }
> > 
> > public class Cachorro extends Animal {
> >     @Override
> >     void respirar() { System.out.println("Respirando pelo pulmão!"); }
> >     void correrAtrasDeCarros() { System.out.println("Au au!"); }
> > }
> > 
> > public class Peixe extends Animal {
> >     @Override
> >     void respirar() { System.out.println("Respirando pelas guelras!"); }
> >     void nadar() { System.out.println("Nadando..."); }
> > }
> > ```
> > 
> > > ⚠️ **Complemento:** Polimorfismo vem do grego "muitas formas". Em Java se manifesta de duas formas:
> > > 
> > > - **Sobrescrita (Override):** a classe filha reescreve o comportamento do método herdado
> > > - **Sobrecarga (Overload):** um mesmo método com parâmetros diferentes na mesma classe
> 
> > [!note]- 🏗️ Arquiteturas Flexíveis — Interfaces, Enum e Generics
> > 
> > - **Interfaces** — definem um contrato de métodos que uma classe deve implementar
> > - **Enum** — define constantes tipadas e seguras
> > - **Generics** — permite criar classes e métodos que funcionam com qualquer tipo de dado
> > 
> > **Enum — Exemplo:**
> > 
> > ```java
> > public enum Feed {
> >     DOG_FOOD,   // vírgula entre constantes
> >     FISH_FOOD;  // ponto e vírgula apenas no último
> > }
> > 
> > List<Pet> pets = new ArrayList<>();
> > pets.add(new Dog());
> > pets.add(new Fish());
> > ```
> > 
> > > ⚠️ **Correção:** Em Enum, as constantes são separadas por **vírgula**, não ponto e vírgula. Apenas o último item leva `;`.
> > 
> > A lista `List<Pet>` só aceita objetos que implementam a interface `Pet`. Se tentar adicionar algo fora disso, o Java lança uma exceção em tempo de compilação, garantindo **segurança de tipos**.
> 
> > [!note]- 📐 Análise, Design e Arquitetura — UML
> > 
> > **UML (Unified Modeling Language)** é um padrão internacional de modelagem visual para software, composto por diagramas que estruturam e descrevem o comportamento de sistemas.
> > 
> > **Usos principais:**
> > 
> > - Análise de requisitos empresariais
> > - Modelagem da estrutura de código (ex: diagrama de classes)
> > - Descrição do tipo de deploy da aplicação (ex: diagrama de implantação)
> > 
> > **Diagramas mais comuns:**
> > 
> > - **Diagrama de Classes** → estrutura do código (classes, atributos, métodos, relações)
> > - **Diagrama de Sequência** → fluxo de chamadas entre objetos
> > - **Diagrama de Casos de Uso** → interação do usuário com o sistema
> 
> > [!abstract]- 📦 Java APIs
> > 
> > |API|Para que serve|
> > |---|---|
> > |Collections|Gerenciar grupos de objetos (listas, filas, conjuntos)|
> > |Streams|Processar e filtrar grandes volumes de dados com lambdas|
> > |IO / NIO|Ler e escrever em arquivos e outras fontes de dados|
> > |Concurrency|Executar tarefas em paralelo com múltiplas threads|
> > |Persistence (JPA)|Conectar e persistir dados em banco de dados|
> > 
> > > [!note]- 🗂️ Collections API
> > > 
> > > A API de Coleções fornece estruturas de dados prontas para gerenciar grupos de elementos.
> > > 
> > > |Estrutura|Característica principal|
> > > |---|---|
> > > |`ArrayList`|Lista baseada em array dinâmico — ótima para acesso por índice|
> > > |`LinkedList`|Lista encadeada — ótima para inserções e remoções frequentes|
> > > |`Set`|Não permite elementos duplicados (ex: `HashSet`, `TreeSet`)|
> > > |`Stack`|LIFO — _Last In, First Out_ (último a entrar, primeiro a sair)|
> > > |`Queue`|FIFO — _First In, First Out_ (primeiro a entrar, primeiro a sair)|
> > > 
> > > **Métodos comuns:**
> > > 
> > > - `add()` → adiciona elemento
> > >     
> > > - `remove()` → remove elemento
> > >     
> > > - `contains()` → busca (retorna true/false)
> > >     
> > > - `sort()` → ordenação
> > >     
> > > - `set(index, elemento)` → atualização
> > >     
> > > 
> > > > ⚠️ **Correção:** Não existe método "Update" na Collections API. Para atualizar usa-se `set(index, novoValor)`.
> > > 
> > > ```java
> > > List<String> nomes = new ArrayList<>();
> > > nomes.add("João");           // adiciona
> > > nomes.remove("João");        // remove
> > > nomes.contains("Maria");     // busca → false
> > > nomes.set(0, "Pedro");       // atualiza índice 0
> > > Collections.sort(nomes);     // ordena
> > > ```
> > 
> > > [!note]- 🌊 Java Streams API
> > > 
> > > Streams são eficientes para **filtrar, mapear e reduzir** grandes volumes de dados, usando **Lambda Expressions**.
> > > 
> > > > **Lambda Expression** = função anônima, sem nome, sem tipo de retorno explícito e sem modificadores de acesso. Usada para implementar **interfaces funcionais** de forma concisa.
> > > 
> > > ```java
> > > List<Employee> employees = new ArrayList<>();
> > > 
> > > employees.stream()
> > >          .parallel()                             // processa em paralelo (múltiplos núcleos)
> > >          .filter(e -> e.getSalary() > 1000)      // filtra quem ganha mais de 1000
> > >          .forEach(e -> e.calculateBonus());       // calcula bônus para cada um
> > > ```
> > > 
> > > **O que esse código faz:**
> > > 
> > > 1. Cria uma lista de funcionários
> > >     
> > > 2. Abre um stream sobre ela
> > >     
> > > 3. `.parallel()` → distribui o processamento entre múltiplos núcleos da CPU
> > >     
> > > 4. `.filter()` → mantém apenas quem tem salário > 1000
> > >     
> > > 5. `.forEach()` → executa `calculateBonus()` para cada funcionário filtrado
> > >     
> > > 
> > > > ⚠️ **Atenção:** `.parallel()` é vantajoso para listas muito grandes. Para listas pequenas pode ser mais lento.
> > > 
> > > **Outros métodos úteis:** `.map()`, `.reduce()`, `.collect()`, `.sorted()`, `.distinct()`
> > 
> > > [!note]- 📁 Java IO API (NIO)
> > > 
> > > Focada em **leitura e escrita de dados** de diferentes fontes (arquivos, rede, teclado) e destinos (terminal, arquivos, banco de dados).
> > > 
> > > ```java
> > > Path file = Path.of("../employee.txt");          // define o caminho do arquivo
> > > Files.lines(file, Charset.forName("UTF-8"))      // lê linha por linha em UTF-8
> > >      .forEach(line -> System.out.println(line));  // imprime cada linha no terminal
> > > ```
> > > 
> > > > ⚠️ **Correção:** No código original, `Files.lines()` usava a variável `readme`, que não havia sido declarada. A variável correta é `file`.
> > > 
> > > **O que esse código faz:**
> > > 
> > > 6. Define o caminho para o arquivo `employee.txt`
> > >     
> > > 7. Lê o arquivo linha por linha em **UTF-8**
> > >     
> > > 8. Imprime cada linha no terminal
> > >     
> > > 
> > > > **Nota:** `Files.lines()` faz parte do pacote `java.nio.file` — versão moderna e mais eficiente da IO API.
> > 
> > > [!note]- ⚙️ Java Concurrency API
> > > 
> > > Permite executar **múltiplas tarefas simultaneamente** (multi-threading), aproveitando os núcleos do processador para melhorar performance e experiência do usuário.
> > > 
> > > ```java
> > > // Define uma tarefa que retorna um BigDecimal
> > > Callable<BigDecimal> taxCalculation = new Callable<>() {
> > >     public BigDecimal call() throws Exception {
> > >         // Fazendo o cálculo de imposto...
> > >         return tax;
> > >     }
> > > };
> > > 
> > > // Cria um pool de threads gerenciado automaticamente
> > > ExecutorService es = Executors.newCachedThreadPool();
> > > 
> > > // Envia a tarefa e guarda referência do resultado futuro
> > > Future<BigDecimal> result = es.submit(taxCalculation);
> > > 
> > > // Busca o resultado quando a tarefa terminar
> > > BigDecimal valor = result.get();
> > > ```
> > > 
> > > **O que esse código faz:**
> > > 
> > > 9. `Callable<BigDecimal>` → define uma tarefa que **retorna um valor**
> > > 10. `ExecutorService` → gerencia um **pool de threads** eficientemente
> > > 11. `newCachedThreadPool()` → cria e reutiliza threads conforme necessário
> > > 12. `Future<BigDecimal>` → representa o **resultado futuro** da tarefa assíncrona
> > > 13. `result.get()` → aguarda e retorna o valor quando a tarefa terminar
> > > 
> > > **Conceitos essenciais:**
> > > 
> > > |Conceito|O que é|
> > > |---|---|
> > > |`Thread`|Unidade básica de execução paralela|
> > > |`ExecutorService`|Gerencia um pool de threads|
> > > |`Callable`|Tarefa que **retorna** um valor|
> > > |`Runnable`|Tarefa que **não retorna** valor|
> > > |`Future`|Referência ao resultado de uma tarefa assíncrona|
> > > |`synchronized`|Evita _race condition_ entre threads|
> > 
> > > [!note]- 🗄️ Java Persistence API (JPA)
> > > 
> > > **JDBC (Java Database Connectivity)** é um protocolo que permite conectar o Java a um banco de dados SQL, comunicando-se via código Java em vez de escrever queries SQL puras.
> > > 
> > > > ⚠️ Para usar JDBC é necessário ter um **driver JDBC** específico do banco (ex: MySQL Connector, PostgreSQL Driver).
> > > 
> > > **JPA** vai além do JDBC — permite mapear classes Java diretamente para tabelas do banco usando anotações:
> > > 
> > > ```java
> > > @Entity
> > > @Table(name = "employees")
> > > public class Employee {
> > > 
> > >     @Id
> > >     @GeneratedValue(strategy = GenerationType.IDENTITY)
> > >     private Long id;
> > > 
> > >     @Column(name = "name")
> > >     private String name;
> > > 
> > >     @Column(name = "salary")
> > >     private BigDecimal salary;
> > > }
> > > ```
> > > 
> > > **Diferença entre JDBC e JPA:**
> > > 
> > > ||JDBC|JPA|
> > > |---|---|---|
> > > |Nível|Baixo nível|Alto nível|
> > > |Como funciona|Queries SQL manuais|Mapeamento objeto-relacional (ORM)|
> > > |Exemplo popular|`java.sql.*`|Hibernate, EclipseLink|

---

_Anotações revisadas e complementadas em 25/04/2026_
## 📦 Java APIs

> Relacionado: [[Collections API]] · [[Streams API]] · [[Concurrency API]] · [[JPA e JDBC]]

| API                   | Para que serve                                           |
| --------------------- | -------------------------------------------------------- |
| **Collections**       | Gerenciar grupos de objetos (listas, filas, conjuntos)   |
| **Streams**           | Processar e filtrar grandes volumes de dados com lambdas |
| **IO / NIO**          | Ler e escrever em arquivos e outras fontes de dados      |
| **Concurrency**       | Executar tarefas em paralelo com múltiplas threads       |
| **Persistence (JPA)** | Conectar e persistir dados em banco de dados             |
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
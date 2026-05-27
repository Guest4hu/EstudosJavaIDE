## 📦 Java APIs

> Relacionado: [[Collections API]] · [[Streams API]] · [[Concurrency API]] · [[JPA e JDBC]]

| API                   | Para que serve                                           |
| --------------------- | -------------------------------------------------------- |
| **Collections**       | Gerenciar grupos de objetos (listas, filas, conjuntos)   |
| **Streams**           | Processar e filtrar grandes volumes de dados com lambdas |
| **IO / NIO**          | Ler e escrever em arquivos e outras fontes de dados      |
| **Concurrency**       | Executar tarefas em paralelo com múltiplas threads       |
| **Persistence (JPA)** | Conectar e persistir dados em banco de dados             |
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

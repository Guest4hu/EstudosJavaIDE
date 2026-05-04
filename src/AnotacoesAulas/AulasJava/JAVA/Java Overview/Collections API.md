## 📦 Java APIs

> Relacionado: [[Collections API]] · [[Streams API]] · [[Concurrency API]] · [[JPA e JDBC]]

| API                   | Para que serve                                           |
| --------------------- | -------------------------------------------------------- |
| **Collections**       | Gerenciar grupos de objetos (listas, filas, conjuntos)   |
| **Streams**           | Processar e filtrar grandes volumes de dados com lambdas |
| **IO / NIO**          | Ler e escrever em arquivos e outras fontes de dados      |
| **Concurrency**       | Executar tarefas em paralelo com múltiplas threads       |
| **Persistence (JPA)** | Conectar e persistir dados em banco de dados             |
## Collections API

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

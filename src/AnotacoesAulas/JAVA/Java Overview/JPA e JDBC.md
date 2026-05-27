## 📦 Java APIs

> Relacionado: [[Collections API]] · [[Streams API]] · [[Concurrency API]] · [[JPA e JDBC]]

| API                   | Para que serve                                           |
| --------------------- | -------------------------------------------------------- |
| **Collections**       | Gerenciar grupos de objetos (listas, filas, conjuntos)   |
| **Streams**           | Processar e filtrar grandes volumes de dados com lambdas |
| **IO / NIO**          | Ler e escrever em arquivos e outras fontes de dados      |
| **Concurrency**       | Executar tarefas em paralelo com múltiplas threads       |
| **Persistence (JPA)** | Conectar e persistir dados em banco de dados             |

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

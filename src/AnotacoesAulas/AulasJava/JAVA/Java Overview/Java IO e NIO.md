### 📁 IO API (NIO)

> Tag: #io | Relacionado: [[Java IO e NIO]]

Focada em **leitura e escrita de dados** de diferentes fontes e destinos.

```java
Path file = Path.of("../employee.txt");          // define o caminho do arquivo
Files.lines(file, Charset.forName("UTF-8"))      // lê linha por linha em UTF-8
     .forEach(line -> System.out.println(line));  // imprime cada linha no terminal
```

> **Nota:** `Files.lines()` faz parte do pacote `java.nio.file` — versão moderna e eficiente da IO API.

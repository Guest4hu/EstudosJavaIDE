# EstudosJavaIDE

Repositório de estudos em Java com foco em prática progressiva: exemplos curtos, exercícios por aula e mini-modelagens de POO.

## Visão geral

- Linguagem principal: Java.
- Estrutura orientada por trilhas de estudo e por número de aula.
- Total mapeado no projeto:
  - `263` arquivos `.java`
  - `2` arquivos `.md`
    - `1` arquivo de modulo IntelliJ (`EstudosJavaIDE.iml`)

## Estrutura do projeto

```text
EstudosJavaIDE/
|-- README.md
|-- EstudosJavaIDE.iml
`-- src/
    |-- LoianeTrainingJavaAulas/
    |   |-- Main.java
    |   |-- OlaMundo.java
    |   |-- licoesdeaula/
    |   `-- licoesdecasa/
    |-- AluraAulas/
    |   `-- aulas/aula1/Exemplo.java
    |-- AnotacoesAulas/
    |   `-- AulasJava/JAVA ORACLE FUNDAMENTIOS.md
    `-- OracleCourseJavaFundamentios/
```

## Proposito de cada pasta principal

### `src/LoianeTrainingJavaAulas`

Nucleo do repositório. Concentra quase todo o codigo Java (`262` arquivos), dividido em:

- `licoesdeaula`: exemplos guiados por assunto.
- `licoesdecasa`: exercicios praticos por bloco de aulas.
- `Main.java` e `OlaMundo.java`: classes introdutorias.

### `src/AluraAulas`

Espaco para exemplos de outra trilha de estudo (`1` arquivo Java em `aula1`).

### `src/AnotacoesAulas`

Notas teóricas em Markdown. Atualmente contem:

- `AulasJava/JAVA ORACLE FUNDAMENTIOS.md` (anotações de fundamentos e APIs Java).

### `src/OracleCourseJavaFundamentios`

Pasta reservada para conteudos futuros (no estado atual, sem arquivos).

## Conteudo detalhado de `LoianeTrainingJavaAulas`

### Aulas teoricas (`licoesdeaula`)

Pastas identificadas: `aula10` ate `aula34` (com alguns numeros nao presentes).

Principais temas por aula:

- `aula10`: variaveis.
- `aula11`: tipos primitivos (`boolean`, `char`, inteiros, ponto flutuante).
- `aula12`: leitura de dados com teclado (`Scanner`).
- `aula13`: operadores aritmeticos, logicos e relacionais.
- `aula14`: estruturas condicionais (`if/else`, `switch`).
- `aula16` e `aula17`: loops e controle de fluxo (`for`, `while`, `break`, `continue`).
- `aula18` a `aula20`: arrays, matriz e `for-each`.
- `aula21` a `aula24`: classes, objetos e metodos (ex.: `Carro`, `Calculadora`).
- `aula26` a `aula29`: associacao, heranca, interfaces e casting.
- `aula30`: tratamento de excecoes (inclusive excecao customizada).
- `aula31`: enums, importacao estatica e utilitarios.
- `aula32` a `aula34`: `printf`, varargs, anotacoes, classe interna e threads.

### Exercicios (`licoesdecasa`)

Conjunto amplo de pratica (`196` arquivos Java), incluindo:

- exercicios numerados (`Exercicio01`, `Exercicio02`, etc.)
- modelagens orientadas a objeto (`Aluno`, `Disciplina`, `ContaCorrente`, `Agenda`)
- hierarquias com heranca/polimorfismo (`Figura2D`, `Figura3D`, `Animal`, `Mamifero`)
- validacoes e excecoes customizadas (`AgendaCheiaExeption`, `ContatoNaoExisteException`)
- utilitarios e metodos estaticos (calculadoras, conversores e contadores)

Distribuicao por bloco de exercicios:

- `aula13`: 15 arquivos
- `aula14_15`: 27 arquivos
- `aula16_17`: 39 arquivos
- `aula18`: 41 arquivos
- `aula19`: 6 arquivos
- `aula21`: 7 arquivos
- `aula22`: 6 arquivos
- `aula23`: 4 arquivos
- `aula24`: 8 arquivos
- `aula25`: 1 arquivo
- `aula26`: 7 arquivos
- `aula27`: 12 arquivos
- `aula28_29`: 12 arquivos
- `aula30`: 5 arquivos
- `aula31`: 2 arquivos
- `aula32`: 1 arquivo

## Como executar

### Opcao 1 - IntelliJ IDEA (recomendado)

1. Abra a pasta `EstudosJavaIDE` como projeto.
2. Aguarde a indexacao do `src`.
3. Abra a classe desejada (qualquer arquivo com `main`).
4. Execute pelo botao de run da IDE.

### Opcao 2 - Terminal (PowerShell)

Exemplo com a classe `LoianeTrainingJavaAulas.OlaMundo`:

```powershell
Set-Location "C:\Users\gusta\OneDrive\Documentos\VISUAL_CODE\EstudosJavaIDE"
javac -d out src\LoianeTrainingJavaAulas\OlaMundo.java
java -cp out LoianeTrainingJavaAulas.OlaMundo
```

Observacao:

- Boa parte dos arquivos possui `main`, mas alguns sao classes de dominio/apoio e dependem de classes de teste.
- A classe `src/AluraAulas/aulas/aula1/Exemplo.java` usa assinatura de `main` diferente do padrao (`main()` sem argumentos) e pode exigir ajuste para execucao direta.

## Requisitos

- JDK 8 ou superior
- IDE Java (IntelliJ, Eclipse ou VS Code com extensoes Java)

## Objetivo do repositorio

Servir como caderno pratico de evolucao em Java, com foco em:

- fundamentos da linguagem
- logica de programacao
- programacao orientada a objetos
- tratamento de excecoes
- organizacao de codigo por modulos de estudo



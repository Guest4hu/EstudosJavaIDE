# Oracle Java Fundamentos
- Aula 25/04/2026
    - Primeiras 4° aulas.
        
        Primeira aula e mais teórica, começando com historia da Java, suas versões e a JVM, que e compilada em C etc., foi feito para ser uma linguagem Orientada a Objetos(POO), da exemplos de JE Enterprise edition etc,
        
        Estamos Começando a explicação do que são classes em JAVA e Objetos, Classes são a representação de algo ou um conceito, ja Objetos e uma instancia, um exemplo especifico da Classe, Exemplo de dois cachorros, Figo e Rex, ambos tem suas peculiaridades, mas são “Filhos” da mesma classe Cachorro.
        
        # Benefícios de Utilizar POO
        
        - Facilidade de reutilizar atributos e métodos entre as hierarquias
        - Classes mais altas tem códigos genéricos que podem ser utilizados pelas classes mais baixas se adequando a sua necessidade.
        
        Ele da o exemplo de uma classe pai chamada Animal, e especifica que ela tem 2 atributos, nome e peso e 2 métodos comer, e respirar, e da o exemplo que o Cachorro e o Peixe irão apenas trocar a maneira como escrevem esta classe, Utilizam os mesmos atributos da classe Animal, e apenas sobrescrevendo da maneira que mais se adequa a sua classe. é como o java interpreta este código durante o tempo de execução, que seria lendo o codigo com base na sua classe atual, apenas herdando o método do pai, mas executando com base nas características da classe atua.
        
        Esta maneira do Java ler e executar o código com base, na classe chamamos de Polimorfismo, onde podemos também adicionar métodos específicos para esta classe, como o cachorro pode ter métodos de correr atras de carros, e o peixe nadar.
        
        # Outras Maneiras para ter Arquiteturas Flexíveis
        
        - Interfaces
        - Enum
        - Generics
        
        ### Enum
        
        ```java
        public enum Feed{
        	DOG_FOOD;
        	FISH_FOOD;
        }
        
        List<Pet> pets = new ArrayList<>();
        pets.add(new Dog());
        pets.add(new Fish());
        ```
        
        A ideia desse código e utilizar o Enum como constantes do tipo de comidas com base no tipo de Filho da classe animal, e a lista que só aceita objetos filhos da Classe Interface Pets, e adicionando a uma lista. isto e para caso queira adicionar algo novo a esta lista, mas não tem a classe Pets, ira ser rejeitada, assim colocando uma excpetion.
        
        # Analises, Design, e Arquitetura.
        
        ### Utilizando UML que é **um padrão internacional de modelagem visual para software, composto por diagramas que estruturam e comportam sistemas, facilitando a documentação e o entendimento técnico**.
        
        Utilizado para facilitar, analises de requerimentos empresariais, modelo de estruturas de codigos, descrever o tipo de Deploy da aplicação.
        
    - Java APIs
        
        # Java, matrizes e coleções.
        
        As API das Coleções, ajuda o desenvolvedor tendo metodos prontos para ajudar a conduzir grupos de elementos, de:
        
        - ArrayList
        - LinkedList
        - Set
        - Stack
        - Queue
        
        Tendo metodos ja pronto como, Add,Update,Remove,Search e rearrange, assim facilitando a maneira como trabalham utilizando vetores e matrizes.
        
        # Java Streams API
        
        São eficientes utilizando, para filtrar, mapear e reduzir grandes quantidades de dados, eles utilizam Lambda Expressions para fazer as ações. é Lambda expressions são métodos sem nome, ou tipo de retorno ou modificadores de acesso, que permitem implementar interfaces funcionais de forma concisa.
        
        ```java
        List<Employee> employees = new ArrayList<>();
        employees.stream().parallel()
        									.filter(e->e.getSalary()>1000)
        									.forEach(e->e.calculateBonus());
        ```
        
        A ideia e utilizar as API para facilitar o uso de grande quantidade de dados como nesse codigo por exemplo, onde pegamos todos os usuários, criamos uma lista, e nesta lista primeiros filtramos todos os usuários com um salario maior que 1000, após isso pegamos todos os valores nesta lista e calculamos o bonus para cada usuario com mais de 1000 em salario.
        
        # JAVA IO API
        
        Essa API e focada em transmitir uma serie de streams conectadas, como no exemplo anterior, ler informações de diferente fontes, e entradas diferentes e escrever informações de diferentes destinos e saídas diferentes.
        
        ```java
        Path file = Path.of("../employee.txt");
        Files.lines(readme, Charset.forName("UTF-8"))
        			.forEach(line->System.out.println(line));
        ```
        
        Por exemplo no código acima, onde temos uma linha de arquivo e transformamos em um Objeto em Java, para após isso vemos o arquivo readme, e procuramos Caracteres legiveis UTF-8, e para cada um prantamos na Saída do terminal.
        
        É com isso, vemos a capacidade de Java em trabalhar com diferentes arquivos e entradas.
        
        # Java Concurrency API
        
        Utilizando processadores, para executar diversos processos diferentes o Java consegue facilitar metodos com perfomance melhor,e melhorar a experiencia do Usuario, utilizando Codigos Multi-Thread
        
        ```java
        Callable<BigDecimal> taxCalculation = new Callable<>() {
        	public BigDecimal call() throws Exception {
        		//Doing Tax Calculation
        		return tax;
        	}
        }
        ExecutorService es = Executors.newCachedThreadPool();
        Future<BigDecimal> result = es.submit(taxCalculation);
        ```
        
        No exemplo acima
    
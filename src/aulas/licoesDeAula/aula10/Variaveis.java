package aulas.licoesDeAula.aula10;

public class Variaveis {
    public static void main(String[] args) {
        //Variaveis com boas praticas convenções de nomeação
        int idade = 21;
        String nome = "Gustavo";
        String nomeCachorro = "Toddy";

        // Variaveis com más práticas de convenções de nomeação
        int IDADE = 21;
        String NOME = "Gustavo";
        String NOME_CACHORRO = "Toddy";

        // Ma prática: variáveis com nomes genéricos ou sem sentido
        int x = 21;
        String y = "Gustavo";
        String z = "Toddy";

        System.out.println("Meu nome é " + nome + " e minha idade é " + idade + " anos.");
        System.out.println("O nome do meu cachorro é " + nomeCachorro + ".");
    }
}

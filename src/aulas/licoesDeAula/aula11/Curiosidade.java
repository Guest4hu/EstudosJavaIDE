package aulas.licoesdeaula.aula11;

public class Curiosidade {
    public static void main(String[] args) {
        int teste1 = 2147483646;
        byte teste2 = 100;
        System.out.println(teste2 + teste1);
        // Saida esperada - 2147483746, mas o resultado é -2147483646, isso acontece porque o valor ultrapassa o limite do tipo int, causando um overflow,  e os numeros e como se fosse uma roleta onde ultrapassa o limite vai para o menor assim por diante.

    }
}

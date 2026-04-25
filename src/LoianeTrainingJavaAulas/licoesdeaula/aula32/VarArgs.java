package LoianeTrainingJavaAulas.licoesdeaula.aula32;

public class VarArgs {
    public static void main(String[] args) {
        System.out.println(soma(1, 2, 3, 4, 5));

    }

    private static double soma(Integer... numeros) {
        double soma = 0;
        for (Integer numero : numeros) {
            soma += numero;
        }
        return soma;
    }
}

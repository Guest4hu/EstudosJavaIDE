package LoianeTrainingJavaAulas.licoesdeaula.aula30;

public class Excecoes {
    public static void main(String[] args) {
        int[] vetor = new int[4];
        System.out.println("Antes da Exception");
        try {
        vetor[4] = 1;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Este indice não existe no vetor");
        }
        System.out.println("Esse texto não sera impresso");
    }
}

package aulas.licoesdeaula.aula30;

public class ExceptionGenerica {
    public static void main(String[] args) {
            int[] vetor = new int[4];
            System.out.println("Antes da Exception");
            try {
                vetor[4] = 1;
            }
            catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Esse texto não sera impresso");
    }
}

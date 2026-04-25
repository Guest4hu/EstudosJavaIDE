package LoianeTrainingJavaAulas.licoesdeaula.aula17;

public class BreakRotulos {
    public static void main(String[] args) {
        int i = 0;
        rotulo1: while (i < 5) {
            int j = 0;
            rotulo2: while (j < 5) {
                if (i == 2 && j == 2) {
                    break rotulo1; // Sai do loop rotulado
                }
                System.out.println("i: " + i + ", j: " + j);
                j++;
            }
            i++;
        }
        System.out.println("Fim do programa.");
    }
}

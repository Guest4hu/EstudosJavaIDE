package LoianeTrainingJavaAulas.licoesdecasa.aula19;

public class Exercicio2 {
    public static void main(String[] args) {
        int[][] matriz = new int[10][10];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) (Math.random() * 100);
            }
        }
        int maiorNumeroLinha7 = Integer.MIN_VALUE;
        int menorNumeroLinha7 = Integer.MAX_VALUE;
        int maiorNumeroLinha5 = Integer.MIN_VALUE;
        int menorNumeroLinha5 = Integer.MAX_VALUE;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i == 7) {
                    if (matriz[i][j] > maiorNumeroLinha7) {
                        maiorNumeroLinha7 = matriz[i][j];
                    }
                    if (matriz[i][j] < menorNumeroLinha7) {
                        menorNumeroLinha7 = matriz[i][j];
                    }
                }
                if (i == 5) {
                    if (matriz[i][j] > maiorNumeroLinha5) {
                        maiorNumeroLinha5 = matriz[i][j];
                    }
                    if (matriz[i][j] < menorNumeroLinha5) {
                        menorNumeroLinha5 = matriz[i][j];
                    }
                }
            }
        }
        System.out.println("Matriz compleata:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Maior número da linha 7: " + maiorNumeroLinha7);
        System.out.println("Menor número da linha 7: " + menorNumeroLinha7);
        System.out.println("Maior número da linha 5: " + maiorNumeroLinha5);
        System.out.println("Menor número da linha 5: " + menorNumeroLinha5);
    }

}

package aulas.licoesdecasa.aula19;

public class Exercicio1 {
    public static void main(String[] args) {
        int[][] matriz = new int[4][4];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) (Math.random() * 100);
            }
        }
        int maiorNumero = 0;
        int MaiorLinha = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] > maiorNumero) {
                    maiorNumero = matriz[i][j];
                    MaiorLinha = i + 1;
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


        System.out.println("Maior Linha: " + MaiorLinha);
        System.out.println("Maior Numero: " + maiorNumero);






    }
}

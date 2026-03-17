package aulas.licoesdecasa.aula19;

import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] matriz = new int[3][3];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.println("Digite um valor para a posição [" + (i + 1) + "][" + (j + 1) + "]:");
                matriz[i][j] = input.nextInt();
            }
        }
        System.out.println("Matriz compleata:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] % 2 == 0) {
                    System.out.println(matriz[i][j] + " é par");
                } else {
                    System.out.println(matriz[i][j] + " é impar");
                }
            }
        }
    }
}

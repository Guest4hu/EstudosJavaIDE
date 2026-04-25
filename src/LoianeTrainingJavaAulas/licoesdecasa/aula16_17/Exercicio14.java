package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio14 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe 10 numero: ");
        int[] numeros = new int[10];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = input.nextInt();
        }
        int soma = 0;
        for (int i = 0; i < numeros.length; i++) {
            soma += numeros[i];
        }
        for (int i = 0; i <= soma; i++) {
            if (i % 2 == 0) {
                System.out.println("O numero " + i + " é par");
            } else {
                System.out.println("O numero " + i + " é impar");
            }
        }






    }
}

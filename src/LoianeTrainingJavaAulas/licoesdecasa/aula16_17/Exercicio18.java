package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio18 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite quantos numeros voce quiser, ate 100 : ");
        int numeros[] = new int[100];
        for (int i = 0; i < numeros.length; i++) {
            int num = input.nextInt();
            if (num == 0) {
                break;
            }
            numeros[i] = num;
        }
        int maiorNumero = numeros[0];
        int menorNumero = numeros[0];

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] > maiorNumero) {
                maiorNumero = numeros[i];
            } else if (numeros[i] < menorNumero && numeros[i] != 0) {
                menorNumero = numeros[i];
            }
        }
        int soma = maiorNumero + menorNumero;
        System.out.println("Maior numero: " + maiorNumero);
        System.out.println("Menor numero: " + menorNumero);
        System.out.println("Soma: " + soma);
    }
}

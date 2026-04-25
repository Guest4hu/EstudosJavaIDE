package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio07 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.println("Me informe 3 numeros: ");
        int num1 = ler.nextInt();
        int num2 = ler.nextInt();
        int num3 = ler.nextInt();

        if (num1 == num2 && num2 == num3) {
            System.out.println("Os numeros são iguais");
        } else if (num1 >= num2 && num1 >= num3 && num2 <= num3) {
            System.out.println("O numero " + num1 + " é o maior e o numero " + num2 + " é o menor");
        } else if (num2 >= num1 && num2 >= num3 && num1 <= num3) {
            System.out.println("O numero " + num2 + " é o maior e o numero " + num1 + " é o menor");
        } else if (num3 >= num1 && num3 >= num2 && num1 <= num2) {
            System.out.println("O numero " + num3 + " é o maior e o numero " + num1 + " é o menor");
        } else if (num1 >= num2 && num1 >= num3 && num3 <= num2) {
            System.out.println("O numero " + num1 + " é o maior e o numero " + num3 + " é o menor");
        } else if (num2 >= num1 && num2 >= num3 && num3 <= num1) {
            System.out.println("O numero " + num2 + " é o maior e o numero " + num3 + " é o menor");
        } else if (num3 >= num1 && num3 >= num2 && num2 <= num1) {
            System.out.println("O numero " + num3 + " é o maior e o numero " + num2 + " é o menor");
        }
         ler.close();
    }
}

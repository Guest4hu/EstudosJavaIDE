package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Me informe 3 numeros: ");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();

        if (num1 == num2 && num2 == num3) {
            System .out.println("Os numeros são iguais");
        } else if (num1 >= num2 && num1 >= num3) {
            System.out.println("O numero " + num1 + " é o maior");
        } else if (num2 >= num1 && num2 >= num3) {
            System.out.println("O numero " + num2 + " é o maior");
        } else {
            System.out.println("O numero " + num3 + " é o maior");
        }
    }
}

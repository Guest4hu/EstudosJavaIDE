package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um numero: ");
        double num = sc.nextDouble();
        boolean numeroQuebrado = num % 1 != 0;
        if (numeroQuebrado) {
            System.out.println("O numero " + num + " é um numero quebrado");
        } else {
            System.out.println("O numero " + num + " é um numero inteiro");
        }
         sc.close();
    }
}

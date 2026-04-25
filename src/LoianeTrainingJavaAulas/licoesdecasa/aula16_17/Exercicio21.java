package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um numero, irei dizer se e primo ou não: ");
        int num = sc.nextInt();
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    System.out.println("O numero " + num + " não é primo");
                    return;
                }
            }
            System.out.println("O numero " + num + " é primo");
         sc.close();
    }
}

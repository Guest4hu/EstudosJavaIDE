package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio35 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe ate qual numero voce quer saber se existem primos: ");
        int numero = input.nextInt();
        for (int i = 1; i <= numero; i++) {
            if (i < 2) {
                System.out.println(i + " nao e primo");
                continue;
            }

            boolean primo = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    primo = false;
                    break;
                }
            }

            if (primo) {
                System.out.println(i + " e primo");
            } else {
                System.out.println(i + " nao e primo");
            }
        }
    }
}

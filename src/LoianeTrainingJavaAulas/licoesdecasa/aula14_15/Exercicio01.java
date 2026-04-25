package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio01 {
        public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.println("Me informe dois numeros : ");
        int num1 = ler.nextInt();
        int num2 = ler.nextInt();

        if (num1 > num2) {
            System.out.println("O numero " + num1 + " é maior que o numero " + num2);
        } else if (num2 > num1) {
            System.out.println("O numero " + num2 + " é maior que o numero " + num1);
        } else {
            System.out.println("Os numeros são iguais");
        }

    }
    
}

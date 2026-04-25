package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o valor do ano que você queira saber se é bissexto ou não: ");
        int ano = sc.nextInt();
        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
            System.out.println("O ano " + ano + " é um ano bissexto.");
        } else {
            System.out.println("O ano " + ano + " não é um ano bissexto.");
        }
         sc.close();



    }
}

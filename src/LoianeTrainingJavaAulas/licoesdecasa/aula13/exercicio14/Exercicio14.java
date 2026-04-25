package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio14;

import java.util.Scanner;

public class Exercicio14 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Joao quantos peixe levara?: ");
        int kgPeixe = input.nextInt();
            if (kgPeixe > 50) {
                int valorExcesso = kgPeixe - 50;
                int multa = valorExcesso * 4;
                System.out.println("Voce esta levando " + valorExcesso + " De peixes a mais a multa sera de " + multa);
            } else {
                System.out.println("Esta tranquilo para levar seu Joao");
            }

    }
}

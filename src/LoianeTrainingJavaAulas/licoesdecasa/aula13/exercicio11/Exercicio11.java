package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio11;

import java.util.Scanner;

public class Exercicio11 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe 2 numeors ");
        int num1 = ler.nextInt();
        int num2 = ler.nextInt();

        double a = (num1 * 2) * (num2 / 2);
        double b = (num1 * 3) + num2;
        double c = num2 * num2 * num2;

        System.out.println("A resultado: " + a + " " + b + " " + c);


    }
}

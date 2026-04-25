package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio12;

import java.util.Scanner;

public class Exercicio12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe sua altura: ");
        double altura = (72.7*input.nextDouble()) - 58;

        System.out.println("Seu peso ideal devera ser: " + altura);


    }
}

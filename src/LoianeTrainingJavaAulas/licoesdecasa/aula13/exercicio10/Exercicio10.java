package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio10;

import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite sua temperatura em celcius: ");
        double celcius = ler.nextDouble() * 1.8 + 32;
        System.out.println("O resultado e " + celcius);



    }
}

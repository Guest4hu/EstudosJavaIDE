package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o numero que voce deseja saber a tabuada: ");
        int num = input.nextInt();

        for (int i = 1; i <= 10; i++) {
            int multiplicacao = num * i;
            System.out.println(num + " x " + i + " = " + multiplicacao);
        }


    }
}

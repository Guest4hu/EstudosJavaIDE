package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe 5 numeros: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();
        int num4 = input.nextInt();
        int num5 = input.nextInt();

        int soma = num1 + num2 + num3 + num4 + num5;
        double media = soma / 5.0;
        System.out.println(soma);
        System.out.println(media);
            input.close();


    }
}

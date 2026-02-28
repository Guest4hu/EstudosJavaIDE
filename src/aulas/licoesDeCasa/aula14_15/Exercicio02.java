package aulas.licoesDeCasa.aula14_15;

import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe um numero : ");
        int num = ler.nextInt();
        if (num > 0) {
            System.out.println("O numero " + num + " é positivo");
        } else if (num < 0) {
            System.out.println("O numero " + num + " é negativo");
        } else {
            System.out.println("O numero é zero");
        }
    }
}

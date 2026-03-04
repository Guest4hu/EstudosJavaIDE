package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio21 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe o valor a ser sacado: ");
        int valor = ler.nextInt();
        if (valor > 600 || valor <= 10) {
            System.out.println("Notas insuficientes para o valor solicitado0");
            return;
        }

        int resto = valor;

        int notas100 = resto / 100;
        resto %= 100;

        int notas50 = resto / 50;
        resto %= 50;

        int notas20 = resto / 20;
        resto %= 20;

        int notas10 = resto / 10;
        resto %= 10;

        int notas5 = resto / 5;
        resto %= 5;

        int notas2 = resto / 2;
        resto %= 2;

        int notas1 = resto;

        System.out.println("Você vai precisar de:");
        System.out.println(notas100 + " nota(s) de 100");
        System.out.println(notas50 + " nota(s) de 50");
        System.out.println(notas20 + " nota(s) de 20");
        System.out.println(notas10 + " nota(s) de 10");
        System.out.println(notas5 + " nota(s) de 5");
        System.out.println(notas2 + " nota(s) de 2");
        System.out.println(notas1 + " nota(s) de 1");
    }
}

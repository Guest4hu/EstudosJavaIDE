package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio13 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("me informe a base: ");
        double base = input.nextDouble();

        System.out.println("me informe o expoente: ");
        int expoente = input.nextInt();

        double resultado =1;

        for (int i =0; i < expoente; i++) {
            resultado *= base;
        }

        System.out.println("a base: " + base + " elevado ao expoente: " + expoente + " é igual a: " + resultado);
        input.close();
    }
}

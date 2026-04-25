package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio15 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Por favor me informe o valor de Ax² - Bx - C em ordem: ");
        int a = ler.nextInt();
        if (a != 0) {
            int b = ler.nextInt();
            int c = ler.nextInt();
            double delta = Math.pow(b, 2) - 4 * a * c;
            if (delta < 0) {
                System.out.println("A equação não possui raízes reais.");
                ler.close();
                System.exit(0);
            } else if ( delta == 0) {
                double raiz = -b / (2.0 * a);
                System.out.println("A equação possui uma raiz real: " + raiz);
                ler.close();
            } else {
                double raiz1 = (-b + Math.sqrt(delta)) / (2.0 * a);
                double raiz2 = (-b - Math.sqrt(delta)) / (2.0 * a);
                System.out.println("A equação possui duas raízes reais: " + raiz1 + " e " + raiz2);
                ler.close();
                System.exit(0);
            }
        } else {
            System.out.println("A não pode ser igual a zero");
            ler.close();
            System.exit(0);
        }
    }
}
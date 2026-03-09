package aulas.licoesdecasa.aula18;

import java.util.Scanner;

public class Exercicio41 {
    public  static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        System.out.println("Valores do array A sem ordem crescente:");
        for (int valorA: a) System.out.println(valorA);
        System.out.println("Valores do array A com ordem crescente:");
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for (int valorA: a) System.out.println(valorA);
    }
}

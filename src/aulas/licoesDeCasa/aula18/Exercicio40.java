package aulas.licoesdecasa.aula18;

import java.util.Scanner;

public class Exercicio40 {
    public  static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }
        System.out.print("Digite um numero: ");
        int numero = input.nextInt();
        for (int valorA : a) {
            if (valorA == numero) {
                System.out.println("O numero " + numero + " existe no array.");
                break;
            }
        }
    }

}

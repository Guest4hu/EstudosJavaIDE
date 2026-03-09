package aulas.licoesdecasa.aula18;

public class Exercicio33 {
    public  static void main(String[] args) {
        int[] a = new int[100];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }

        for (int valorA : a) {
            System.out.print("Todos os numeros pares e imapar ate " + valorA + ": ");
            for (int i = 1; i <= valorA; i++) {
                if (i % 2 == 0) {
                    System.out.println("Esse número é par: " + i);
                } else {
                    System.out.println("Esse número é ímpar: " + i);
                }
            }
        }
    }
}

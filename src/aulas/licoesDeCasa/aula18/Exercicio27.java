package aulas.licoesdecasa.aula18;

public class Exercicio27 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[]b = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int i = a.length - 1, j = 0; i >= 0; i--, j++) {
            b[j] = a[i];
        }
        for (int valorA: a) System.out.println(valorA);
        System.out.println("Array b copiado:");
        for (int valorB: b) System.out.println(valorB);
    }

}

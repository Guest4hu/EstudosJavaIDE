package aulas.licoesdecasa.aula18;

public class Exercicio7 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = new int[10];
        int[] c = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
            b[i] = (int) (Math.random() * 100);
        }
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] - b[i];
        }
        for (int valorA: a) System.out.println(valorA);
        System.out.println("Array b copiado:");
        for (int valorB: b) System.out.println(valorB);
        System.out.println("Array c copiado:");
        for (int valorC: c) System.out.println(valorC);
    }

}

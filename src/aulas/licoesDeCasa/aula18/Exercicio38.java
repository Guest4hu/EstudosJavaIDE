package aulas.licoesdecasa.aula18;

public class Exercicio38 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = new int[10];
        int[] c = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10);
            b[i] = (int) (Math.random() * 10);
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] == b[j]) {
                    c[i] = a[i];
                } else {
                    c[i] = 0;
                }
            }
        }



        System.out.println("A = ");
        for (int valorA: a) System.out.println(valorA);
        System.out.println("B = ");
        for (int valorB: b) System.out.println(valorB);
        System.out.println("C = ");
        for (int valorC: c) System.out.println(valorC);

    }
}

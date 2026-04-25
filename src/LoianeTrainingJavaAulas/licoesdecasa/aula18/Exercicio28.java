package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio28 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = new int[10];
        int[] c = new int[20];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
            b[i] = (int) (Math.random() * 100);
        }

        for (int i = 0; i < c.length; i++) {
            if (c.length / 2 > i) {
                c[i] = a[i];
            } else {
                c[i] = b[i-10];
            }
        }
        System.out.println("A = ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\nB = ");
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println("\nC = ");
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
            }

    }

}

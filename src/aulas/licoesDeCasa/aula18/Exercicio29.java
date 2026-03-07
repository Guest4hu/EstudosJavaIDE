package aulas.licoesdecasa.aula18;

public class Exercicio29 {
    public static void main(String[] args) {
        int[] a = new int[20];
        int[] b = new int[20];
        int[] c = new int[20];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);

        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                b[i] = a[i];
                c[i] = 0;
            } else {
                c[i] = a[i];
                b[i] = 0;
            }
        }
        System.out.println("A = ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("B = ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println("C = ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(c[i] + " ");
        }
    }

}

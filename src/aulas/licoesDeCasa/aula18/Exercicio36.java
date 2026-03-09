package aulas.licoesdecasa.aula18;

public class Exercicio36 {
    public  static void main(String[] args) {
        int[] a = new int[5];
        int[] b = new int[5];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10);
        }


        for (int i = 0; i < a.length; i++) {
            b[i] = calcularFatorial(a[i]);
        }

        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);


        }

    }

    public static int calcularFatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int fatorial = 1;
        for (int i = n; i != 1; i--) {
            fatorial *= i;
        }
        return fatorial;
    }
}

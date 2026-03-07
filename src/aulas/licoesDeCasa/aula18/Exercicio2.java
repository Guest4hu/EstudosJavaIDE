package aulas.licoesdecasa.aula18;

public class Exercicio2 {
    public static void main(String[] args) {
        int[] a = new int[8];
        int[] b = new int[8];
        a[0] = 1;
        a[1] = 0;
        a[2] = 5;
        a[3] = -2;
        a[4] = -5;
        a[5] = 7;
        a[6] = 0;
        a[7] = 3;
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] * 2;
        }
        for (int valor: b) {
            System.out.println(valor);
        }


    }
}

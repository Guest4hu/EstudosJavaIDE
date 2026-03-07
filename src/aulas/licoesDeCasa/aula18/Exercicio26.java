package aulas.licoesdecasa.aula18;

public class Exercicio26 {
    public static void main(String[] ars) {
        int[] a = new int[10];
        char[] b = new char[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 11);
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] < 7) {
                b[i] = 'a';
            } else if (a[i] == 7) {
                b[i] = 'b';
            } else if (a[i] < 10){
                b[i] = 'c';
            } else if (a[i] == 10) {
                b[i] = 'd';
            } else  {
                b[i] = 'e';
            }
        }
        for (int valorA: a) System.out.println(valorA);
        System.out.println("Array b copiado:");
        for (char valorB: b) System.out.println(valorB + " ");

    }

}

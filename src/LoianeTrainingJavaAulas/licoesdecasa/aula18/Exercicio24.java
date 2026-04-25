package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio24 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[]b = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int i = 0; i < b.length; i++) {
            if (a[i] % 2 == 0) {
                b[i] = 1;
            } else {
                b[i] = 0;
            }
        }
        for (int valorA: a) System.out.println(valorA);
        System.out.println("Array b copiado:");
        for (int valorB: b) System.out.println(valorB);

    }

}

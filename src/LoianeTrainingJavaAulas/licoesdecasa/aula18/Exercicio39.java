package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio39 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[] b = new int[10];
        int[] c = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10);
            b[i] = (int) (Math.random() * 10);
        }

        System.out.println("Os numeros que nn se repetem em A e B é: ");
        for (int i = 0; i < a.length; i++) {
            boolean repetido = false;
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    repetido = true;
                    break;
                }
            }
            if (!repetido) {
                c[i] = a[i];
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

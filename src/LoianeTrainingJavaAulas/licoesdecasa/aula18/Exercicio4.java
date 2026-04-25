package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio4 {
    public static void main(String[] args) {
        int[] a = new int[15];
        int[] b = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }
        for (int i = 0; i < a.length; i++) {
            b[i] = (int) Math.sqrt(a[i]);
        }
        for (int valorA: a) System.out.println(valorA);
        System.out.println("Array b copiado:");
        for (int valorB: b) System.out.println(valorB);
    }
}

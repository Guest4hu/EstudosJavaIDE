package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio37 {
    public static void main(String[] args) {
        int[] a = new int[5];
        int[] b = new int[5];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10);
        }

        for (int i = 0; i < b.length; i++) {
            int soma = 0;
            for (int j = i; j <a.length; j++) {
                soma += a[j];
            }
            System.out.println(soma);
            b[i] = soma;
        }
        System.out.println("Array a:");
        for (int valorA: a) System.out.println(valorA);
        System.out.println("Array b copiado:");
        for (int valorB: b) System.out.println(valorB);
    }

}

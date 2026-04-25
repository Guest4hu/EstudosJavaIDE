package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio14 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int soma = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int valorA: a) {
           soma += valorA;
        }
        System.out.println("media dos valores do array: " + (soma/a.length));
    }

}

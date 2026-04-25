package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio35 {
    public  static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) Math.pow(2, i+1); // Gerar os quadrados dos números de 1 a 10
        }

        for (int valorA : a) {
            System.out.println(valorA);
        }
    }
}

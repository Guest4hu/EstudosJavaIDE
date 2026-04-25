package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio20 {
    public static void main(String[] args) {
        double[] a = new double[20];
        double cotacaoDolar = 5.25;
        for (int i = 0; i < a.length; i++) {
            a[i] = cotacaoDolar * (i + 1);
        }
        for (double valor: a) {
            System.out.println(valor);
        }
    }
}

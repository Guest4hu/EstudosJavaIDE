package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio31 {
    public  static void main(String[] args) {
        int[] a = new int[5];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 20);
        }

        for (int valorA: a) {
            for (int i = 0; i <= 10; i++) {
                System.out.println("Tabuada do " + valorA + ": " + valorA * i + " ");
            }
        }



    }

}

package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio13 {
    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int valorA: a) {
            if (valorA % 5 == 0) {
                System.out.println("é multiplo de 5: " + valorA);
            } else {
                System.out.println("não é: " + valorA);
            }
        }
    }
}

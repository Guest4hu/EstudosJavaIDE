package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio23 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int[]b = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
       }

        for (int i = a.length - 1, j = 0; i >= 0; i--, j++) {
            b[j] = a[i];
        }


        for (int i = a.length - 1, j = 0; i >= 0; i--, j++) {
            if (a[i] != b[j]) {
                System.out.println("O array não é palindromo");
                return;
            }
        }
        System.out.println("O array é palindromo!");
    }

}

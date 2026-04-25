package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio21 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int soma = 0;
        int valores1 = 0;
        int valores2 = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) Math.round(Math.random() * 1);
        }

        for (int valorA: a) {
            System.out.println(valorA + " ");
            if (valorA == 1) {
                valores1++;
            } else {
                valores2++;
            }
            soma += valorA;
        }
        System.out.println(" a porcentagem de 1 é: " + (valores1 * 100.0 / a.length) + "%");
        System.out.println(" a porcentagem de 0 é: " + (valores2 * 100.0 / a.length) + "%");
    }

}

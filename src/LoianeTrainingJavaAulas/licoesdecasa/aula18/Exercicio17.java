package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio17 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int superior35 = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int valorA: a) {
            System.out.println(valorA);
            if (valorA > 35) superior35++;
        }
        System.out.println("Quantidade de pessoas com idade superiores a 35: " + superior35);

    }

}

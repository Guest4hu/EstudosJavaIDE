package aulas.licoesdecasa.aula18;

public class Exercicio32 {
    public  static void main(String[] args) {
        int[] a = new int[100];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }

        for (int valorA: a) {
            for (int i = 2; i <= Math.sqrt(valorA); i++) {
                if (valorA % i != 0) {
                    System.out.println("Esse número é primo: " + valorA);
                    break;
                } else {
                    System.out.println("Esse número não é primo: " + valorA);
                    break;
                }

            }
        }



    }

}

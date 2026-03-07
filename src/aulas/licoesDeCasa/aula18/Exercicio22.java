package aulas.licoesdecasa.aula18;

public class Exercicio22 {
    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int valorA: a) {
            if (valorA % 2 == 0) {
                System.out.println("Número par: " + valorA);
            } else {
                System.out.println("Número ímpar: " + valorA);
                break;
            }
        }
    }

}

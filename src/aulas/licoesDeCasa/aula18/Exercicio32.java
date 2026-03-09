package aulas.licoesdecasa.aula18;

public class Exercicio32 {
    public  static void main(String[] args) {
        int[] a = new int[100];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }

        for (int valorA : a) {
            if (valorA <= 1) {
                System.out.println("Esse número não é primo: " + valorA);
                continue;
            }
            boolean primo = true;
            for (int i = 2; i <= Math.sqrt(valorA); i++) {
                if (valorA % i == 0) {
                    primo = false;
                    break;
                }
            }
            if (primo) {
                System.out.println("Esse número é primo: " + valorA);
            } else {
                System.out.println("Esse número não é primo: " + valorA);
            }
        }
    }
}

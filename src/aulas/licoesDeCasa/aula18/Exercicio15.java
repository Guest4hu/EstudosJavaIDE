package aulas.licoesdecasa.aula18;

public class Exercicio15 {
    public static void main(String[] args) {
        int[] a = new int[10];
        int valorPar = 0;
        int valorimpar = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int valorA: a) {
            if (valorA % 2 == 0) {
                valorPar++;
            } else {
                valorimpar++;
            }
        }
        System.out.println("A porcentagem de números pares é: " + (valorPar * 100.0 / a.length) + "%");
        System.out.println("A porcentagem de números ímpares é: " + (valorimpar * 100.0 / a.length) + "%");
    }

}

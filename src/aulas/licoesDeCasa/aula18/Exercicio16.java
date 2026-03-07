package aulas.licoesdecasa.aula18;

public class Exercicio16 {
    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int valorA: a) {
            if (valorA < 15) {
                System.out.println("é menor que 15: " + valorA);
            } else if (valorA == 15) {
                System.out.println("é igual a 15: " + valorA);

            } else if (valorA > 15) {
                System.out.println("é maior que 15: " + valorA);
            }
        }
    }

}

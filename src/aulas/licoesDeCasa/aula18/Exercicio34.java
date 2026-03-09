package aulas.licoesdecasa.aula18;

public class Exercicio34 {
    public  static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 200) + 1; // Gerar números entre 1 e 200
        }

        for (int valorA : a) {
            System.out.println("Todos os divisores de " + valorA + ": ");
            for (int i = 2; i <= valorA; i++) {
                if (valorA % i == 0) {
                    System.out.println("Este número é divisor: " + i);
                } else {
                }
            }
        }
    }
}

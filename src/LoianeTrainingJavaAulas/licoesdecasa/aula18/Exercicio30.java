package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio30 {
    public  static void main(String[] args) {
        int[] a = new int[20];
        int[] b = new int[20];
        int valorPar = 0;


        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                valorPar++;
            }
        }

        int posPar = 0;
        int posImpar = valorPar;

        for (int num : a) {
            if (num % 2 == 0) {
                b[posPar++] = num;
            } else {
                b[posImpar++] = num;
            }
        }


        for (int valorA : a) System.out.println(valorA);
        System.out.println("Array b copiado:");
        for (int valorB : b) System.out.println(valorB);
    }
}

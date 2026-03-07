package aulas.licoesdecasa.aula18;

public class Exercicio3 {
    public static void main(String[] args) {
        int[] a = new int[15];
        int[] b = new int[15];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] * a[i];
        }
        for (int valor: a) System.out.println(valor);
        System.out.println("Array b copiado:");
        for (int valor: b) System.out.println(valor);



    }
}

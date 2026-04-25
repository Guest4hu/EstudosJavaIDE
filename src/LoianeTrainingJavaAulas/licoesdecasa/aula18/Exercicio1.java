package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio1 {
    public static void main(String[] args) {
        int[] a = new int[5];
        int[] b = new int[5];
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;
        a[3] = 4;
        a[4] = 5;

        // Copiar valores de 'a' para 'b'
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        // Exibir resultado (opcional)
        System.out.println("Array b copiado:");
        for (int valor : b) {
            System.out.print(valor + " ");
        }
    }
}

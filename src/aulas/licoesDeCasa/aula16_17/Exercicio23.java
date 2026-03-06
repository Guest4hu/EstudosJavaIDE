package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio23 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe um numero e eu direi os primos ate ele: ");
        int numero = input.nextInt();

        for (int i = 1; i <= numero; i++) {
            if (i < 2) {
                System.out.println(i + " nao e primo");
                continue;
            }

            boolean primo = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    primo = false;
                    break;
                }
            }

            if (primo) {
                System.out.println(i + " e primo");
            } else {
                System.out.println(i + " nao e primo");
            }
        }

        input.close();
    }
}

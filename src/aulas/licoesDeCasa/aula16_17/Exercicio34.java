package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio34 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe um numero irei dizer se e primo ou não: ");
        int num = input.nextInt();
        boolean primo = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                primo = false;
                break;
            }
        }
        if (primo) {
            System.out.println("O numero " + num + " é primo");
        } else {
            System.out.println("O numero " + num + " não é primo");
        }
         input.close();

    }
}

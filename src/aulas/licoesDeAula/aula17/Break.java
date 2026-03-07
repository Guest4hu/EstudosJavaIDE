package aulas.licoesdeaula.aula17;

import java.util.Scanner;

public class Break {
    public  static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite um numero: ");
        int num = input.nextInt();
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                System.out.println("O numero " + num + " não é primo");
                break;
            }
            if (i == num - 1) {
                System.out.println("O numero " + num + " é primo");
            }
        }
        System.out.print("Fim do programa");
            input.close();
    }
}

package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um numero, irei dizer se e primo ou não: ");
        int num = sc.nextInt();
        if(num%2==0){
            System.out.print("O numero " + num + " não e primo ");
        }


        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                System.out.println("Ele pode ser divido por " + i);
            }
        }
        if (num % 2 != 0){
            System.out.print("O numero " + num + " é primo");
        }
        sc.close();
    }

}

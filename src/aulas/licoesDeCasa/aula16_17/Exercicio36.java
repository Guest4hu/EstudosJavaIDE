package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio36 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("De qual tabuada voce deseja saber?: ");
        int num = input.nextInt();
        System.out.println("Qual numero deseja começar e em qual numero deseja parar?: ");
        int comeco = input.nextInt();
        int fim = input.nextInt();
        if (comeco > fim){
            System.out.println("O numero de começo deve ser menor que o numero de fim");
            return;
        } else if (comeco <= 0) {
            System.out.println("O numero de começo deve ser maior que 0");
            return;
        } else if (num <= 0) {
            System.out.println("O numero da tabuada deve ser maior que 0");
            return;
        }
        for (int i = comeco; i <= fim; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }
}

package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio17 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe um numero para eu calcular o fatorial dele: ");
        int num = input.nextInt();
        for (int i = num; i != 1 ; i--){
            num *= (i - 1);
        }
        System.out.println(num);
    }
}

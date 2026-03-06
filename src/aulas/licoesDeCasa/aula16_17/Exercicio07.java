package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio07 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Me informe 5 numeros e eu direi o maior: ");
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();
        int num4 = input.nextInt();
        int num5 = input.nextInt();

        int maior = num1;
        if (num2 > maior) {
            maior = num2;
        }
        if (num3 > maior) {
            maior = num3;
        }
        if (num4 > maior) {
            maior = num4;
        }
        if (num5 > maior) {
            maior = num5;
        }
        System.out.println("O maior numero é: " + maior);
         input.close();
    }
}

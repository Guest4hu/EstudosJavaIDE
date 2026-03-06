package aulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int  num1;
        int num2;

        while(true){
            System.out.println("Digite dois numero: ");
            num1 = input.nextInt();
            num2 = input.nextInt();
            if (num1 == num2){
                System.out.println("Os numeros são iguais, por favor digite novamente");
            } else {
                break;
            }
        }

        int maior;
        int menor;

        if (num1 > num2) {
            maior = num1;
            menor = num2;
        } else {
            maior = num2;
            menor = num1;
        }



        for (int i = menor, j = maior; i <= maior; i++, j--){
            System.out.println("Contador: " + i);
            System.out.println("Contador: " + j);
        }




    }
}

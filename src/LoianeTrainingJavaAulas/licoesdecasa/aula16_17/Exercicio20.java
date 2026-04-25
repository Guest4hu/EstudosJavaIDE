package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio20 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
        System.out.println("Me informe um numero para eu calcular o fatorial dele: ");
        int num = input.nextInt();
        if(num<0 || num > 16){
            System.out.println("O numero deve ser entre 0 e 20, por favor digite novamente");
            continue;
        }



        for (int i = num; i != 1 ; i--){
            num *= (i - 1);
        }
        System.out.println(num);

        System.out.println("Deseja calcular outro fatorial? (S/N)");
        String resposta = input.next();
        if (resposta.equalsIgnoreCase("N")) {
            break;
        } else if (resposta.equalsIgnoreCase("S")) {
            // Continua o loop para calcular outro fatorial
        } else {
                System.out.println("Resposta inválida, encerrando o programa.");
                break;
        }
        }
        input.close();
        System.out.print("Sistema encerrado: ");
    }
}
package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio01 {
    // Faça um programa que peça uma nota de 0 a 10, e mostre uma mensagem caso o valor seja inválido e continue pedindo até que o usuário informe um valor válido.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nota;
        while(true){
            System.out.println("Digite uma nota: ");
            nota = input.nextInt();
            if(nota >= 0 && nota <= 10){
                System.out.println("Nota válida: " + nota);
                break;
            } else {
                System.out.println("Valor inválido. Por favor, digite uma nota entre 0 e 10.");
            }
        }
    }



//        do {
//            System.out.println("Digite uma nota de 0 a 10: ");
//            nota = input.nextInt();
//            if (nota < 0 || nota > 10) {
//                System.out.println("Valor inválido. Por favor, digite uma nota entre 0 e 10.");
//            }
//        } while (nota < 0 || nota > 10);
//        System.out.println("Nota válida: " + nota);
//         input.close();
//    }



}

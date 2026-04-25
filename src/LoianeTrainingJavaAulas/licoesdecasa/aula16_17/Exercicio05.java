package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio05 {
    public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
        double populacaoA;
        double taxaCrescimentoA;
        double populacaoB;
        double taxaCrescimentoB;
        while (true) {

            System.out.println("Digite a quantidade da populaçao do pais A: ");
            populacaoA = input.nextDouble();
            System.out.println("Digite a taxa de crescimento do pais A:  EX: 0.03 para 3% ");
            taxaCrescimentoA = input.nextDouble();
            System.out.println("Digite a quantidade da populaçao do pais B: ");
            populacaoB = input.nextDouble();
            System.out.println("Digite a taxa de crescimento do pais B: ");
            taxaCrescimentoB = input.nextDouble();

            if (populacaoA <= 0 || taxaCrescimentoA <= 0 || populacaoB <= 0 || taxaCrescimentoB <= 0) {
                System.out.println("As populações e as taxas de crescimento devem ser maiores que zero.");
            } else if (populacaoA >= populacaoB && taxaCrescimentoA <= taxaCrescimentoB) {
                System.out.println("O pais A nunca ultrapassará o pais B com as condições atuais.");
            } else {
                break;
            }
        }
        System.out.print("Em qual ano estamos? ");
        int anoAtual = input.nextInt();
        while (populacaoA <= populacaoB) {
            populacaoA *= (1 + taxaCrescimentoA);
            populacaoB *= (1 + taxaCrescimentoB);
            anoAtual++;
        }
        System.out.println("Paises A: " + populacaoA);
    System.out.println("Paises B: " + populacaoB);
    System.out.println("Anos: " + anoAtual);
    }




    }

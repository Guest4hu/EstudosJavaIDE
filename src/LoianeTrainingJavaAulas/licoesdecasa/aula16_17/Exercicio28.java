package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a quantidade de cd: ");
        int quantidadeTurmas = sc.nextInt();
        System.out.print("Digite o valor de cada cd: ");
        int quantidadeAlunos = 0;
        for (int i = 0; i < quantidadeTurmas; i++) {
            System.out.print("Digite o valor pago no cd °" + (i + 1) + ": ");
            int alunos = sc.nextInt();
            if (alunos < 0) {
                System.out.print("\nValor invalido por favor digite novamente.\n");
                i--; // Decrementa o contador para repetir a entrada para a mesma turma
                continue;
            }
            quantidadeAlunos += alunos;
        }
        System.out.println("O valor total dos CD é: " + quantidadeAlunos );

    }

}

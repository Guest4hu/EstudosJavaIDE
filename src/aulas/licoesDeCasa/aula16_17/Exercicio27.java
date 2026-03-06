package aulas.licoesdecasa.aula16_17;

import java.lang.foreign.SymbolLookup;
import java.util.Scanner;

public class Exercicio27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a quantidade de turmas: ");
        int quantidadeTurmas = sc.nextInt();
        System.out.print("Digite a quantidade de alunos por turmas: ");
        int quantidadeAlunos = 0;
        for (int i = 0; i < quantidadeTurmas; i++) {
            System.out.print("Digite a quantidade na turma " + (i + 1) + ": ");
            int alunos = sc.nextInt();
            if (alunos > 40 || alunos < 0) {
                System.out.print("\nQuantidade de alunos inválida. Por favor, digite um número entre 0 e 40.\n");
                i--; // Decrementa o contador para repetir a entrada para a mesma turma
                continue;
            }
            quantidadeAlunos += alunos;
        }
        System.out.println("A quantidade total de alunos é: " + quantidadeAlunos + " alunos.");
        System.out.println("A quantidade media de alunos por turma e de" + (quantidadeAlunos / quantidadeTurmas) + " alunos por turma.");

    }


}

package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio14 {
    public static void main(String[] args) {

    Scanner ler = new Scanner(System.in);
    System.out.println("Digite as notas do aluno: ");
    double nota1 = ler.nextDouble();
    double nota2 = ler.nextDouble();
    double mediaNota = (nota1 + nota2) / 2;
    char conceito;
    if (mediaNota >= 9.0) {
        conceito = 'A';
    } else if (mediaNota >= 7.5) {
        conceito = 'B';
    } else if (mediaNota >= 6.0) {
        conceito = 'C';
    } else if (mediaNota >= 4.0) {
        conceito = 'D';
    } else {
        conceito = 'E';
    }

    if (mediaNota >= 6) {
        System.out.println("Média: " + mediaNota + " - Conceito: " + conceito + " - Aprovado");
    } else {
        System.out.println("Média: " + mediaNota + " - Conceito: " + conceito + " - Reprovado");
    }
    ler.close();
    }
}

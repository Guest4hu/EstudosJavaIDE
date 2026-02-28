package aulas.licoesDeCasa.aula13.exercicio8;

import java.util.Scanner;

public class Exercicio8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Quanto voce ganha por hora e a quantidade de horas trabalhada no mes: ");
        double horas = input.nextDouble();
        double salarioPorHoras = input.nextDouble();
        double total = salarioPorHoras * horas;

        System.out.println("O seu salario nesse mes e de " + total);


    }
}

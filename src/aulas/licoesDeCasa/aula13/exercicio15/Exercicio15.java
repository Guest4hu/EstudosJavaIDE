package aulas.licoesdecasa.aula13.exercicio15;

import java.util.Scanner;

public class Exercicio15 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("quanto voce ganha por hora trabalhada, e quantas horas voce trabalhou?: ");
        double horasTrabalhadas = ler.nextDouble();
        double valorHoras = ler.nextDouble();
        double salarioBruto = horasTrabalhadas * valorHoras;
        double inss = salarioBruto * 0.08;
        double ir = salarioBruto * 0.11;
        double sindicato = salarioBruto * 0.05;
        double total = salarioBruto - (inss + ir + sindicato);
        System.out.println(" Voce pagou para o inss " + inss + " Voce pagou para o imposto de renda " + ir + "Voce pagou para o sindicato " + sindicato + "e sobrou apenas do seu salario: " + total);





    }
}

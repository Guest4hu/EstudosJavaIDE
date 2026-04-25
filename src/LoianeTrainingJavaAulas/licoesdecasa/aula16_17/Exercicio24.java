package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio24 {
    public static void main(String[] args) {
        ArrayList<Double> notas = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("Digite a nota: ");
            double nota = input.nextDouble();
            notas.add(nota);

            System.out.println("Deseja continuar? (s/n): ");
            continuar = input.next();
            }

        double soma = 0;
        for (int i = 0; i < notas.size(); i++) {
            soma += notas.get(i);
        }
        double media = soma / notas.size();
        System.out.print("A media do aluno e: " + media);
        input.close();
    }
}

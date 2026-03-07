package aulas.licoesdecasa.aula16_17;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio33 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String continuar = "s";
        ArrayList<Double> lista = new ArrayList<>();
        double soma = 0;
        double maior = 0;
        double menor = 0;
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("Qual o valor da temperatura em Celsius? ");
            double celsius = input.nextDouble();
            lista.add(celsius);
            soma += celsius;
            System.out.println("Deseja continuar? (s/n): ");
            continuar = input.next();
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) <= menor) {
                menor = lista.get(i);
            } else if (lista.get(i) >= maior) {
                maior = lista.get(i);
            }
        }
        double media = soma / lista.size();
        System.out.println("A media das temperaturas e: " + media);
        System.out.println("A maior temperatura e: " + maior);
        System.out.println("A menor temperatura e: " + menor);
            input.close();
    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> idade = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("Digite a idade: ");
            int nota = input.nextInt();
            idade.add(nota);

            System.out.println("Deseja continuar? (s/n): ");
            continuar = input.next();
        }

        int soma = 0;
        for (int i = 0; i < idade.size(); i++) {
            soma += idade.get(i);
        }
        double media = soma/ idade.size();
        System.out.println("A média das idades é: " + media);

        if (media < 25) {
            System.out.println("Turma jovem");
        } else if (media >= 25 && media <= 40) {
            System.out.println("Turma adulta");
        } else {
            System.out.println("Turma idosa");
        }
    }
}

package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio25 {
    static void main() {
        int participation = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Responda 5 perguntas com sim ou nao: \n");
        System.out.print("Telefonou para a vítima? ");
        String resposta1 = sc.nextLine().toLowerCase();
        System.out.print("Esteve no local do crime? ");
        String resposta2 = sc.nextLine().toLowerCase();
        System.out.print("Mora perto da vítima? ");
        String resposta3 = sc.nextLine().toLowerCase();
        System.out.print("Devia para a vítima? ");
        String resposta4 = sc.nextLine().toLowerCase();
        System.out.print("Já trabalhou com a vítima? ");
        String resposta5 = sc.nextLine().toLowerCase();

        if (resposta1.equals("sim")) {
            participation++;
        }
        if (resposta2.equals("sim")) {
            participation++;
        }
        if (resposta3.equals("sim")) {
            participation++;
        }
        if (resposta4.equals("sim")) {
            participation++;
        }
        if (resposta5.equals("sim")) {
            participation++;
        }
        switch (participation) {
            case 1:
                System.out.println("Inocente");
                break;
            case 2:
                System.out.println("Suspeito");
                break;
            case 3:
            case 4:
                System.out.println("Cúmplice");
                break;
            case 5:
                System.out.println("Assassino");
                break;
                default:
                System.out.println("Inocente");
        }


    }


}

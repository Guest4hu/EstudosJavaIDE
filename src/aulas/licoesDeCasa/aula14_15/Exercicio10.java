package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Em qual turno você estuda? [M]- Manha - [T] - tarde [N} noturno: ");
        String turno = ler.next().toUpperCase();

        switch (turno) {
            case "M":
                System.out.println("Bom dia!");
                break;
            case "T":
                System.out.println("Boa tarde!");
                break;
            case "N":
                System.out.println("Boa noite!");
                break;
            default:
                System.out.println("Turno invalido!");
                break;
        }
        ler.close();
    }
}

package aulas.licoesdecasa.aula23.ex1;

import java.util.Scanner;

public class TesteLampada {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Lampada lanpada = new Lampada("Indel", "Circular", "Amarelo", false);
        System.out.println("Marca: " + lanpada.getMarca());
        System.out.println("Modelo: " + lanpada.getModelo());
        System.out.println("Cor: " + lanpada.getColor());
        System.out.println("Ligada: " + lanpada.isLigado());
        System.out.println("Deseja ligar lampada? [S/N]");
        String resposta = input.nextLine();
        if (resposta.equalsIgnoreCase("S")) {
            lanpada.setLigado(true);
            System.out.println("Lampada ligada: " + lanpada.isLigado());
        } else {
            System.out.println("Lampada permanece desligada: " + lanpada.isLigado());
        }
    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio18 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite uma data: ex 21/10/2005: ");
        String data = ler.nextLine();
        String[] partes = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);

        if ( dia < 0 || dia > 31 || mes < 1 || mes > 12 || ano < 0) {
            System.out.println("Data inválida");
        } else {
            System.out.println("Data válida");
        }
         ler.close();
    }
}

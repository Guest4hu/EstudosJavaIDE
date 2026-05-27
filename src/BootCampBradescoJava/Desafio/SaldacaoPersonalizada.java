package BootCampBradescoJava.Desafio;

import java.util.Scanner;

public class SaldacaoPersonalizada {
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Lê a linha de entrada e separa em nome e tipo de conta
            String input = scanner.nextLine();
            String[] partes = input.split(" ");

            String nome = partes[0];
            String tipoConta = partes[1].toLowerCase();


            boolean valido = false;
            if (tipoConta.equals("corrente") || tipoConta.equals("poupanca") || tipoConta.equals("investimento")) valido = true;

            System.out.println(
                                (valido) ? "Bem-vindo(a), "+nome+"! Sua conta "+tipoConta+" esta pronta para uso."
                                        : "Tipo de conta invalido."
                                );
            scanner.close();
        }
    }
}

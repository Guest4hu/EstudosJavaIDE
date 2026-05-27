package BootCampBradescoJava.Desafio;

import java.util.Scanner;

public class VerificarSaldo {
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Lê os dois valores inteiros da entrada (saldo e valor da transação)
            int saldo = scanner.nextInt();
            int valorTransacao = scanner.nextInt();

            System.out.println((saldo > valorTransacao) ? "Transacao aprovada" : "Saldo insuficiente");

            scanner.close();
        }
    }


}

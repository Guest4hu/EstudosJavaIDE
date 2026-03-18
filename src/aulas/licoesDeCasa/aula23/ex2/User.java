package aulas.licoesdecasa.aula23.ex2;

import java.util.Scanner;

public class User {
    Scanner ler = new Scanner(System.in);


    public void main() {
        ContaCorrente contaCorrente = new ContaCorrente(1000.0, "João Silva", "12345-6", "001", 500.0, true);
        boolean running = true;
        while (running) {
            System.out.println("Bem Vindo ao banco opções:");
            System.out.println("1 - Verificar Saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Sair");
            int opcao = ler.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Saldo: " + contaCorrente.getSaldo());
                    break;
                case 2:
                    System.out.println("Digite o valor a ser depositado: ");
                    double valor = ler.nextDouble();
                    contaCorrente.realizarDeposito(valor);
                    break;
                case 3:
                    System.out.println("Digite o valor a ser sacado: ");
                    double valorSaque = ler.nextDouble();
                    contaCorrente.realizarSaque(valorSaque);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    running = false;
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }


        }
    }
}
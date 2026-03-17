package aulas.licoesdecasa.aula22.ex2;

import java.util.Scanner;

public class User {
    private ContaCorrente contaCorrente;
    Scanner ler = new Scanner(System.in);
    void constructor() {
        contaCorrente = new ContaCorrente();
        contaCorrente.id = 1;
        contaCorrente.saldo = 1000.0;
        contaCorrente.especial = true;
        contaCorrente.limiteEspecial = 500.0;
    }


    public void main() {
        constructor();
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
                    System.out.println("Saldo: " + this.contaCorrente.getSaldo());
                    break;
                case 2:
                    System.out.println("Digite o valor a ser depositado: ");
                    double valor = ler.nextDouble();
                    this.contaCorrente.realizarDeposito(valor);
                    break;
                case 3:
                    System.out.println("Digite o valor a ser sacado: ");
                    double valorSaque = ler.nextDouble();
                    this.contaCorrente.realizarSaque(valorSaque);
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
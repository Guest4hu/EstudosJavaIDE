package BootCampBradescoJava.exercicios.JavaClassesEncapsulamento.ex1;

import java.util.Scanner;

public class Teste {

    private static int option = -1;
    private static ContaBancaria conta = (new ContaBancaria(new Pessoa("Joao", 1), "12345-6", 1000));
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        do {
            menu();
            option = lerInputInt();
            acaoMenu();
        } while (option != 0);
    }
    private static int lerInputInt() {

        while (true) {

            try {
                return Integer.parseInt(scanner.next());

            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero inteiro.");
            }
        }
    }

    private static double lerInputDouble() {

        while (true) {

            try {
                return Double.parseDouble(scanner.next());

            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero real.");
            }
        }
    }

    private static void menu() {
        System.out.println("Bem vindo ao Banco, " + conta.getTitular().nome() + "!");
        System.out.println("6 - Verificar se a conta está usando cheque especial.");
        System.out.println("5 - Pagar um boleto.");
        System.out.println("4 - Sacar dinheiro;");
        System.out.println("3 - Depositar dinheiro;");
        System.out.println("2 - Consultar cheque especial");
        System.out.println("1 - Consultar saldo");
        System.out.println("0 - Sair");
    }

    private static void acaoMenu() {
        switch (option) {
            case 1 -> consultarSaldo();
            case 2 -> consultarChequeEspecial();
            case 3 -> depositarDinheiro();
            case 4 -> sacarDinheiro();
            case 5 -> pagarBoleto();
            case 6 -> verificarChequeEspecial();
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opcao invalida. Por favor, escolha uma opcao valida.");
        }
    }

    private static void verificarChequeEspecial() {
        System.out.println("Verificando se a conta está usando cheque especial...");
        if (conta.isUsingChequeEspecial()) {
            System.out.println("A conta está usando cheque especial.");
            System.out.println("Valor do cheque especial: R$ " + conta.getChequeEspecialUsed());
        } else {
            System.out.println("A conta não está usando cheque especial.");
        }
    }

    private static void pagarBoleto() {
        System.out.println("Digite o valor do boleto:");
        double valorBoleto = lerInputDouble();

        try {
            conta.pagarBoleto(valorBoleto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void sacarDinheiro() {
        System.out.println("Digite o valor a ser sacado:");
        double valorSaque = lerInputDouble();

        try {
        conta.sacar(valorSaque);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void depositarDinheiro() {
        System.out.println("Digite o valor a ser depositado:");
        double valorDeposito = lerInputDouble();
        try {
            conta.depositar(valorDeposito);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarChequeEspecial() {
        System.out.println("Consultando cheque especial...");
        System.out.println(conta.getChequeEspecial());
    }
    private static void consultarSaldo() {
        System.out.println("Consultando saldo...");
        System.out.printf("Saldo atual: R$%.2f%n", conta.getSaldo());
    }

}

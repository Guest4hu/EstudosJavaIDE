package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio28 {
        static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Por favor me informe a quantidade de Carne, e o tipo da carne [F] - file duplo - [A] - Alcatra - [P] - picanha : ");
        double quantidade = input.nextDouble();
        String tipo = input.next().toUpperCase();
        double valorTotal = calculaValor(quantidade, tipo);

        if (valorTotal > 0) {
            aplicarDesconto(quantidade, tipo, valorTotal);
        }
    }

    public static double calculaValor(double quantidade, String tipo){
        double valorTotal = 0;
        if (tipo.equals("F")){
            if (quantidade <= 5) {
                valorTotal = quantidade * 4.90;
            } else {
                valorTotal = quantidade * 5.80;
            }
        } else if (tipo.equals("A")){
            if (quantidade <= 5) {
                valorTotal = quantidade * 5.90;
            } else {
                valorTotal = quantidade * 6.80;
            }
        } else if (tipo.equals("P")){
            if (quantidade <= 5) {
                valorTotal = quantidade * 6.90;
            } else {
                valorTotal = quantidade * 7.80;
            }
        } else {
            System.out.println("Tipo de carne invalida");
        }
        return valorTotal;
    }
    public static void aplicarDesconto(double quantidade, String tipo, double valorTotal) {
        System.out.print("Metodo de pagamento [C] - cartao tabajara - [D] - dinheiro - [O] - outro: ");
        String metodoPagamento = input.next().toUpperCase();

        double desconto = 0;
        double valorFinal = valorTotal;
        String tipoNome = "";

        if (metodoPagamento.equals("C")) {
            desconto = valorTotal * 0.05;
            valorFinal = valorTotal - desconto;
        }

        if (tipo.equals("F")) tipoNome = "File Duplo";
        else if (tipo.equals("A")) tipoNome = "Alcatra";
        else if (tipo.equals("P")) tipoNome = "Picanha";

        System.out.println("\n=== CUPOM FISCAL ===");
        System.out.println("Tipo de Carne: " + tipoNome);
        System.out.println("Quantidade: " + quantidade + " kg");
        System.out.println("Preço Total: R$ " + String.format("%.2f", valorTotal));
        System.out.println("Desconto: R$ " + String.format("%.2f", desconto));
        System.out.println("Tipo de Pagamento: " + (metodoPagamento.equals("C") ? "Cartão Tabajara" : "Outro"));
        System.out.println("Valor a Pagar: R$ " + String.format("%.2f", valorFinal));
        System.out.println("===================");
    }
}

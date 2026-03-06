package aulas.licoesdecasa.aula16_17;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio31 {
    public static void main(String[] args) {
        System.out.println("Loja Tabajara");
        ArrayList produtos = new ArrayList();
        Scanner input = new Scanner(System.in);
        double produtoAtual = 1;
        while (produtoAtual != 0) {
            System.out.print("Digite o valor do produto: " );
            produtos.add(input.nextDouble());
            produtoAtual = (double) produtos.getLast();
        }
        double total = 0;
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("Produto " + (i + 1) + ": R$ " + produtos.get(i));
                total += (double) produtos.get(i);
        }
        System.out.print("Total: R$ ");
        System.out.println("Valor total: R$ " + total);
         input.close();
    }
}

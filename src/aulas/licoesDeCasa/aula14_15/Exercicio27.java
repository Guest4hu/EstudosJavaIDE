package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a quantidade de morango, e maça em KG: ");
        double morango = sc.nextDouble();
        double maca = sc.nextDouble();
        if(morango >= 0 && maca >= 0){
            double total = getTotal(morango, maca);
            System.out.println("O valor total a ser pago é: " + total);

        } else {
            System.out.println("Valor invalido, por favor digite um valor maior que 0");
        }
         sc.close();
    }

    private static double getTotal(double morango, double maca) {
        double precoMorango = 0;
        double precoMaca = 0;
        if (morango <= 5) {
            precoMorango = morango * 2.50;
        } else {
            precoMorango = morango * 2.20;
        }
        if (maca <= 5) {
            precoMaca = maca * 1.80;
        } else {
            precoMaca = maca * 1.50;
        }
        double total = precoMorango + precoMaca;
        if (total > 25 || (morango + maca) > 8) {
            total = total * 0.90;
        }
        return Math.round(total * 100.0) / 100.0;
    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio17;

import java.util.Scanner;

public class Exercicio17 {
    public static void main(String[] args){

        Scanner ler = new Scanner(System.in);

        System.out.println("Digite a quantidade de metros quadrados a ser pintado: ");
        double metrosQuadrados = ler.nextDouble();


        double litrosNecessarios = metrosQuadrados / 6;


        litrosNecessarios *= 1.10;


        double latas = Math.ceil(litrosNecessarios / 18);
        double precoLatas = latas * 80;


        double galoes = Math.ceil(litrosNecessarios / 3.6);
        double precoGaloes = galoes * 25;


        double latasMistura = Math.floor(litrosNecessarios / 18);
        double restante = litrosNecessarios - (latasMistura * 18);

        double galoesMistura = Math.ceil(restante / 3.6);

        double precoMistura = (latasMistura * 80) + (galoesMistura * 25);


        System.out.println("\n===== RESULTADO =====");

        System.out.println("\nApenas latas:");
        System.out.println("Quantidade: " + latas);
        System.out.println("Preço: R$ " + precoLatas);

        System.out.println("\nApenas galões:");
        System.out.println("Quantidade: " + galoes);
        System.out.println("Preço: R$ " + precoGaloes);

        System.out.println("\nMisturando latas e galões:");
        System.out.println("Latas: " + latasMistura);
        System.out.println("Galões: " + galoesMistura);
        System.out.println("Preço: R$ " + precoMistura);

        ler.close();
    }
}
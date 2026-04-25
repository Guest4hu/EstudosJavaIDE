package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;
import java.util.Locale;
public class exercicio26{
    public static void main(String[] args) {
        System.out.print("Me informe a quantidade de litros é a o titpo de combustivel [A] alcool - [G] gasolina: ");
        Scanner ler = new Scanner(System.in);
        double litros = ler.nextDouble();
        if(litros < 0){
            System.out.print("Valor de litros invalido");
            return;
        }
        String tipo = ler.next().toUpperCase(Locale.getDefault());
            if (tipo.equals("G")){
                calculoGasolina(litros);
            } else if (tipo.equals("A")){
                calculoAlcool(litros);
            } else {
                System.out.println("Tipo de combustivel invalido");
            }

    }
    public static void calculoGasolina(double litros) {
        double valorFinal;
        if (litros > 20) {
            valorFinal = litros * (2.50 * 0.94);
        } else {
            valorFinal = litros * (2.50 * 0.96);
        }
        System.out.println("O valor a ser pago é: " + valorFinal);
    }
    public static void calculoAlcool(double litros){
        double valorFinal;
        if (litros > 20) {
            valorFinal = litros * (1.90 * 0.95);
        } else {
            valorFinal = litros * (1.90 * 0.97);
        }
        System.out.println("O valor a ser pago é: " + valorFinal);
    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio19 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe um numero menor que mil e direi a unidades dele : ");
        int num = ler.nextInt();
        if (num < 1000 && num >= 100) {
            int centenas = num / 100;
            int dezenas = (num % 100) / 10;
            int unidades = num % 10;
            System.out.println("O numero " + num + " tem " + centenas + " centenas e " + dezenas + " dezenas" + " e " + unidades + " unidades");
        } else if (num > 10 && num < 100) {
            int dezenas = num / 10;
            int unidades = num % 10;
            System.out.println("O numero " + num + " tem " + dezenas + " dezenas" + " e " + unidades + " unidades");
        } else if (num > 0) {
            System.out.println("o seu numero tem " + num + " unidades");
        } else {
            System.out.println("Numero invalido");
        }
    }
}

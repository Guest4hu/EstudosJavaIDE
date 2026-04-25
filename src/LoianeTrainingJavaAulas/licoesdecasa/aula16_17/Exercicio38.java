package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio38 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o salario do funcionario: ");
        double salario = input.nextDouble();
        double reajuste = 0.015;
        System.out.print("Em ate que ano vode deseja calcular o reajuste? ");
        int ano = input.nextInt();
        for (int i = 1995; i <= ano; i++, reajuste *= 2) {
            salario += salario * reajuste;
            System.out.println("Salario do funcionario no ano de " + i + " é de: R$ " + salario);
        }





    }

}

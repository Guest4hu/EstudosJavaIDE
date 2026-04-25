package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio12 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe quantitade de horas trabalhadas, em seguida o valor da hora trabalhada: ");
        double horasTrabalhadas = ler.nextDouble();
        double valorHora = ler.nextDouble();
        double salarioBruto = horasTrabalhadas * valorHora;
        double descontoIr = 0.0;
        double descontoInss = salarioBruto * 0.10;
        double descontoFgts = salarioBruto * 0.11;
        if (salarioBruto > 2500){
            descontoIr = salarioBruto * 0.20;
        } else if (salarioBruto > 1500){
            descontoIr = salarioBruto * 0.10;
        } else if (salarioBruto > 900){
            descontoIr = salarioBruto * 0.05;
        } else {
            descontoIr = 0.0;
        }
        double salarioLiquido = salarioBruto - descontoIr - descontoInss;
        System.out.println("Salário Bruto: R$ " + salarioBruto);
        System.out.println("Desconto IR: " + (descontoIr/salarioBruto*100) + "% - R$ " + descontoIr);
        System.out.println("Desconto INSS: " + (descontoInss/salarioBruto*100) + "% - R$ " + descontoInss);
        System.out.println("Desconto FGTS: " + (descontoFgts/salarioBruto*100) + "% - R$ " + descontoFgts); 
        System.out.println("Total de descontos: R$ " + (descontoIr + descontoInss));
        System.out.println("Salário Líquido: R$ " + salarioLiquido);
    }
}

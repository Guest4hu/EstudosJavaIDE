package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite dois numeros: ");
        double num1 = sc.nextDouble();
        double num2 = sc.nextDouble();
         System.out.println("Qual operação deseja realizar? [+] - [-[ - [x] - [/]: ");
        String operacao = sc.next();
        double resultado = realizarOperacao(num1, num2, operacao);
        parOuImpar(resultado);
        inteiroOuQuebrado(resultado);
         sc.close();
    }
    public static double realizarOperacao(double num1, double num2, String operacao) {
        double resultado = 0;
        switch (operacao) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "x":
                resultado = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                      resultado = num1 / num2;
                } else {
                    System.out.println("Não é possível dividir por zero.");
                    return resultado;
                }
                break;
            default:
                System.out.println("Operação inválida.");
                return resultado;
        }
        System.out.println("O resultado da operação " + operacao + " entre " + num1 + " e " + num2 + " é: " + resultado);
        return resultado;
    }


    public static void parOuImpar(double valor) {
        if (valor % 2 == 0) {
            System.out.println("O numero " + valor + " é par");
        } else {
            System.out.println("O numero " + valor + " é impar");
        }
    }

     public static void inteiroOuQuebrado(double num) {
        boolean numeroQuebrado = num % 1 != 0;
        if (numeroQuebrado) {
            System.out.println("O numero " + num + " é um numero quebrado");
        } else {
            System.out.println("O numero " + num + " é um numero inteiro");
        }
    }


}

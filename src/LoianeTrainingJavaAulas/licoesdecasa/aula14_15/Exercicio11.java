package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio11 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        boolean respostaUser = true;

        while (respostaUser){
            System.out.println("Me informe seu salario: ");
            float salario = ler.nextFloat();
            if (salario > 1500) {
                float novoSalario = salario * 1.05f;
                System.out.println("Seu salario antigo era de R$ " + salario + " e seu novo salario é de R$ " + novoSalario + " com um aumento de 5%");
            } else if (salario >= 700) {
                float novoSalario = salario * 1.10f;
                System.out.println("Seu salario antigo era de R$ " + salario + " e seu novo salario é de R$ " + novoSalario + " com um aumento de 10%");
            } else if (salario >= 280) {
                float novoSalario = salario * 1.15f;
                System.out.println("Seu salario antigo era de R$ " + salario + " e seu novo salario é de R$ " + novoSalario + " com um aumento de 15%");
            } else {
                float novoSalario = salario * 1.20f;
                System.out.println("Seu salario antigo era de R$ " + salario + " e seu novo salario é de R$ " + novoSalario + " com um aumento de 20%");
            }
            System.out.println("Deseja continuar? [S/N]");
            String resposta = ler.next().toUpperCase();
            if (resposta.equals("S")) {
            } else if (resposta.equals("N")) {
                respostaUser = false;
            } else {
                System.out.println("Resposta invalida, encerrando o programa.");
                respostaUser = false;
            }
        }
        ler.close();
    }
}

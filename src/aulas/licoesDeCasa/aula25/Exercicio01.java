package aulas.licoesdecasa.aula25;

import java.util.Scanner;

public class Exercicio01 {
    private static int soma;
     static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Digite o numero da sequencia de FIBONASI que deseja saber");
        System.out.println("O resultado é: " + sequenciaDeFibonati(ler.nextInt()));
        System.out.println("Digite o numero para o somatorio");
        System.out.println("O resultado é: " + somatorioDoNumero(ler.nextInt()));
    }
    public static double sequenciaDeFibonati(int numero){
        if (numero <= 0) {
            return 0;
        } else if (numero == 1) {
            return 1;
        } else {
            return sequenciaDeFibonati(numero - 1) + sequenciaDeFibonati(numero - 2);
        }
    }


    public static int somatorioDoNumero(int numero){
        soma+=numero;
        if (numero == 1){
            return soma;
        }
        return somatorioDoNumero(numero - 1);
    }
}

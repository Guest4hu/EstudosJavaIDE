package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;
import java.util.Scanner;

public class Exercicio22 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe um numero inteiro: ");
        int num = ler.nextInt();
        if (num % 2 == 0) {
            System.out.println("O numero " + num + " é par");
        } else {
            System.out.println("O numero " + num + " é impar");
        }
         ler.close();



    }
}

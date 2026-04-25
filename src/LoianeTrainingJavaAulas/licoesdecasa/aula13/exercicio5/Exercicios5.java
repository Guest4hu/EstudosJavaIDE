package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio5;
import java.util.Scanner;
public class Exercicios5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o valor em CM do que você quer converter para metros: ");
        double valorEmCM = input.nextDouble();
        double valorEmMetros = valorEmCM / 100;

        System.out.println("o valor em metros é: " + valorEmMetros);

    }
}

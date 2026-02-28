package aulas.licoesdecasa.aula13.exercicio3;
import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("POr favor me informe o  1° numero para eu fazer a soma: ");
        int numero1 = input.nextInt();
        System.out.println("POr favor me informe o 2° numero para eu fazer a soma: ");
        int numero2 = input.nextInt();

        int resultado = numero1 + numero2;
        System.out.println("O resultado da soma é: " + resultado);

    }
}

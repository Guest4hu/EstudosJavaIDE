package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio16 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Por favor me informe os 3 valores de um triangulo: ");
        double lado1 = ler.nextDouble();
        double lado2 = ler.nextDouble();
        double lado3 = ler.nextDouble();
        if (lado1 < lado2 && lado2 < lado3) {
            System.out.println("Os lados formam um triangulo escaleno");
        } else if (lado1 == lado2 && lado2 == lado3) {
            System.out.println("Os lados formam um triangulo equilatero");
        } else if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
            System.out.println("Os lados formam um triangulo isosceles");
        } else {
            System.out.println("Os lados não formam um triangulo");
        }



    }
}

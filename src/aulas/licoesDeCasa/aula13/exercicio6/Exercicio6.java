package aulas.licoesdecasa.aula13.exercicio6;

import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o valor do raio do circulo: ");
        int raio = input.nextInt();
        double areaCirculo = (raio * raio) * 3.14;
        System.out.println("A area do seu circulo e de " + areaCirculo);

    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio7;

import java.util.Scanner;

public class Exercicio7 {
    public static void main(String[] args) {
    Scanner ler = new Scanner(System.in);

    System.out.println("Digite o valor da largura do quadrado: ");
    int largura = ler.nextInt();

    System.out.println("Digite o valor da Altura do quadrado: ");
    int altura = ler.nextInt();


    int area = altura * altura;
    int dobroArea = area * 2;

    System.out.println("A area do seu circulo e de " + area);
    System.out.println("E o dobro e: " + dobroArea);



    }
}

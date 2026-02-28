package aulas.licoesdecasa.aula13.exercicio16;
import java.util.Scanner;

public class Exercicio16 {
    public static void main(String[] args) {
    Scanner ler = new Scanner(System.in);

    System.out.println("Digite a quantidade de metros quadrado a ser pintado: ");
    double litroPorMetroQuadrado = ler.nextInt() / 3;
    double quantidadeDeLataDeTintas = litroPorMetroQuadrado / 18;
    double precoTotal = quantidadeDeLataDeTintas * 80;
    System.out.println(" Voce precisara de " + quantidadeDeLataDeTintas + " Latas de Tintas, o valor total sera de " + precoTotal);



    }
}

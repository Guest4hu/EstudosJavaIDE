package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio9;
import  java.util.Scanner;


public class Exercicio9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a temperatura que voce deseja tranformar em celcius: ");
        double temperatura = (sc.nextDouble() - 32)/1.8;
        System.out.println("Temperatura: " + temperatura);


    }
}

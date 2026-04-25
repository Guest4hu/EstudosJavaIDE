package LoianeTrainingJavaAulas.licoesdecasa.aula13.exercicio4;
import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Por favor me informe as notas do seu aluno: ");
        int nota1 = input.nextInt();
        int nota2 = input.nextInt();
        int nota3 = input.nextInt();
        int nota4 = input.nextInt();

        double media = (nota1 + nota2 + nota3 + nota4) / 4;

        System.out.println(media);
    }
}

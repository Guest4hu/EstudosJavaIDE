package aulas.licoesdeaula.aula14;
import java.util.Scanner;


class ifElse{
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe sua idade por favor: ");
        int idade = ler.nextInt();
        
        if (idade >= 18) {
            System.out.println("Você é maior de idade, pode entrar!");
        } else {
            System.out.println("Você é menor de idade, não pode entrar!");
        }
        ler.close();
    }
}


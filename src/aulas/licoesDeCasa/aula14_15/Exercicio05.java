package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

class Exercise05 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Me informe as duas notas do aluno : ");
        double nota1 = ler.nextDouble();
        double nota2 = ler.nextDouble();
        double media = (nota1 + nota2) / 2;

        if (media == 10){
        System.out.println("Aprovado com Distinção");
        } else if (media >= 7) {
            System.out.println("Aprovado");
        } else {
            System.out.println("Reprovado");
        }
        ler.close();

    }

}

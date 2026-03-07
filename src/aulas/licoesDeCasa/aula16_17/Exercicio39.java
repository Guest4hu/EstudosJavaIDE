package aulas.licoesdecasa.aula16_17;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio39 {
    public static void main(String[] args) {
        ArrayList<Double> alunos = new ArrayList<>();
        ArrayList<Integer> codigo = new ArrayList<>();
        double alunoMaior = Integer.MIN_VALUE;
        int codigoAlunoMaior = 0;

        Scanner input = new Scanner(System.in);
        for(int i = 0; i < 10; i++) {
            System.out.println("Digite o codigo do aluno " + (i + 1) + ": ");
            int cod = input.nextInt();
            codigo.add(cod);
            double altura = input.nextDouble();
            alunos.add(altura);
        }

        for(int i = 0; i < alunos.size(); i++) {
            if(alunos.get(i) > alunoMaior) {
                alunoMaior = alunos.get(i);
                codigoAlunoMaior = codigo.get(i);
            }
        }

        System.out.println("Altura do maior aluno: " + alunoMaior);
        System.out.println("Codigo do aluno: " + codigoAlunoMaior);










    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula26.ex2;

import java.util.Scanner;

public class Teste {
    private final static int QUANTIDADE_DE_ALUNOS = 5;
    private final static int QUANTIDADE_NOTAS_DE_ALUNOS = 4;
    private final static int NOTA_PARA_PASSAR = 7;
    private static double MEDIA_DA_TURMA = 0;
    private static double MEDIA_DO_ALUNO;
    private static Curso curso = new Curso();
    private static Professor professor = new Professor();
    private static Aluno[] alunos = new Aluno[QUANTIDADE_DE_ALUNOS];
    private static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        professor.setNome("Reinaldo");
        professor.setEmail("Reinaldo@dlasdklasd.com");
        professor.setDepartamento("T.I");
        curso.setNome("Engenharia de Software");
        curso.setProfessor(professor);
        curso.setDataHorario("Segunda e Quarta das 19:00 às 21:00");


        for (int i = 0; i < QUANTIDADE_DE_ALUNOS; i++) {
            Aluno aluno = new Aluno();
            System.out.println("Digite o nome do aluno " + (i + 1) + ":");
            aluno.setNome(input.nextLine());
            aluno.setMatricula(curso.getNome());
            double[] notas = new double[QUANTIDADE_NOTAS_DE_ALUNOS];
            for (int j = 0; j < QUANTIDADE_NOTAS_DE_ALUNOS; j++) {
                System.out.println("Digite a nota " + (j + 1) + " do aluno " + (i + 1) + ":");
                notas[j] = Double.parseDouble(input.nextLine());
            }
            aluno.setNotas(notas);
            alunos[i] = aluno;
        }
        curso.setAlunos(alunos);

        System.out.println("Curso: " + curso.getNome());
        System.out.println("Professor: " + professor.getNome());
        for (Aluno aluno : curso.getAlunos()) {
            MEDIA_DO_ALUNO = 0;
            System.out.println("Aluno: " + aluno.getNome());
            for (double notas: aluno.getNotas()) {
                MEDIA_DO_ALUNO += notas;
                MEDIA_DA_TURMA += notas;
                System.out.println("Notas: " + notas);
            }
            MEDIA_DO_ALUNO = MEDIA_DO_ALUNO / QUANTIDADE_NOTAS_DE_ALUNOS ;
            if (MEDIA_DO_ALUNO >= NOTA_PARA_PASSAR) {
                System.out.println("Situação: Aprovado");
            } else {
                System.out.println("Situação: Reprovado");
            }
            System.out.println("Nota: " + MEDIA_DA_TURMA);
        }
        System.out.println("Media da turma: " + MEDIA_DA_TURMA/(QUANTIDADE_NOTAS_DE_ALUNOS * QUANTIDADE_DE_ALUNOS));
    }

}

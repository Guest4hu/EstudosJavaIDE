package aulas.licoesdecasa.aula22.ex3;

import java.util.Locale;
import java.util.Scanner;

public class Disciplina {

    public static void main(String[] args) {
        Locale.setDefault(Locale.getDefault());
        Scanner scanner = new Scanner(System.in);

        Aluno aluno = new Aluno();

        System.out.print("Digite o nome do aluno: ");
        aluno.setNome(scanner.nextLine());

        System.out.print("Digite a matricula do aluno: ");
        aluno.setMatricula(scanner.nextLine());

        System.out.print("Digite o curso do aluno: ");
        aluno.setCurso(scanner.nextLine());

        System.out.println("\nCadastro das " + Aluno.QUANTIDADE_DISCIPLINAS + " disciplinas:");

        for (int i = 0; i < Aluno.QUANTIDADE_DISCIPLINAS; i++) {
            System.out.print("Nome da disciplina " + (i + 1) + ": ");
            String nomeDisciplina = scanner.nextLine();
            aluno.setDisciplina(i, nomeDisciplina);

            System.out.print("Nota em " + nomeDisciplina + ": ");
            double nota = scanner.nextDouble();
            scanner.nextLine(); // limpa o enter pendente
            aluno.setNota(i, nota);
        }

        System.out.println("\n===== RESULTADO =====");
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Matricula: " + aluno.getMatricula());
        System.out.println("Curso: " + aluno.getCurso());

        for (int i = 0; i < Aluno.QUANTIDADE_DISCIPLINAS; i++) {
            String disciplina = aluno.getDisciplina(i);
            double nota = aluno.getNota(i);
            String situacao = aluno.isAprovadoEm(i) ? "Aprovado" : "Reprovado";

            System.out.println(
                    "Disciplina: " + disciplina +
                            " | Nota: " + nota +
                            " | Situacao: " + situacao
            );
        }
        scanner.close();
    }
}
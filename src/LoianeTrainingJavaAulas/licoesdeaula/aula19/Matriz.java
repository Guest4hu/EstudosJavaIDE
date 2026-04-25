package LoianeTrainingJavaAulas.licoesdeaula.aula19;

public class Matriz {
    public static void main(String[] args) {
        double[][] notasAlunos = new double[30][4];
        for (int i = 0; i < notasAlunos.length; i++) {
            for (int j = 0; j < notasAlunos[i].length; j++) {
                notasAlunos[i][j] = Math.random() * 10;
            }
        }
        for (int i = 0; i < notasAlunos.length; i++) {
            System.out.println("Notas do aluno " + (i + 1) + ":");
            for (int j = 0; j < notasAlunos[i].length; j++) {
                System.out.println("Nota " + (j + 1) + ": " + notasAlunos[i][j]);
            }
        }
    }
}

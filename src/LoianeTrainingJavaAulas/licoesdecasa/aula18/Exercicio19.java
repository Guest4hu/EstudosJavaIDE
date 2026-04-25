package LoianeTrainingJavaAulas.licoesdecasa.aula18;

public class Exercicio19 {
    public static void main(String[] args) {
        int[] alunos = new int[10];
        double[] notasAlunos = new double[10];
        double[] notasAlunos2 = new double[10];
        double[] media = new double[10];


        for (int i = 0; i < alunos.length; i++) {
            double soma = 0;
            alunos[i] = i + 1;
            notasAlunos2[i] = (Math.random() * 10);
            notasAlunos[i] = (Math.random() * 10);
            soma = notasAlunos[i] + notasAlunos2[i];
            media[i] = soma / 2;
        }

        for (int i = 0; i < alunos.length; i++) {
            System.out.println(alunos[i] + " Aluno");
            System.out.println(notasAlunos[i] + " 1 nota");
            System.out.println(notasAlunos2[i] + " 2 nota ");
            System.out.println(media[i] + " Média ");
             if (media[i] >= 7) {
                System.out.println("Aluno aprovado");
            } else {
                System.out.println("Aluno reprovado");
            }
             System.out.println(" ");
        }



    }

}

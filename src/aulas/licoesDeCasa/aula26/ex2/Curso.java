package aulas.licoesdecasa.aula26.ex2;

public class Curso {
    private String nome;
    private String dataHorario;
    private Professor professor;
    private Aluno[] alunos;

    public Curso(String nome, String dataHorario, Professor professor, Aluno[] alunos) {
        this.nome = nome;
        this.dataHorario = dataHorario;
        this.professor = professor;
        this.alunos = alunos;
    }

    public Curso() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(String dataHorario) {
        this.dataHorario = dataHorario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno[] getAlunos() {
        return alunos;
    }

    public void setAlunos(Aluno[] alunos) {
        this.alunos = alunos;
    }
}

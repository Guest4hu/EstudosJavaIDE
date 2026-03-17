package aulas.licoesdecasa.aula22.ex3;

public class Aluno {
    public static final int QUANTIDADE_DISCIPLINAS = 3;
    public static final double NOTA_MINIMA_APROVACAO = 7.0;

    private String nome;
    private String matricula;
    private String curso;
    private String[] disciplinas;
    private double[] notas;
    private boolean statusAtivo;

    public Aluno() {
        this.disciplinas = new String[QUANTIDADE_DISCIPLINAS];
        this.notas = new double[QUANTIDADE_DISCIPLINAS];
        this.statusAtivo = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

    public String getDisciplina(int indice) {
        validarIndice(indice);
        return disciplinas[indice];
    }

    public void setDisciplina(int indice, String disciplina) {
        validarIndice(indice);
        disciplinas[indice] = disciplina;
    }

    public double getNota(int indice) {
        validarIndice(indice);
        return notas[indice];
    }

    public void setNota(int indice, double nota) {
        validarIndice(indice);
        notas[indice] = nota;
    }

    public boolean isAprovadoEm(int indiceDisciplina) {
        validarIndice(indiceDisciplina);
        return notas[indiceDisciplina] >= NOTA_MINIMA_APROVACAO;
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= QUANTIDADE_DISCIPLINAS) {
            throw new IllegalArgumentException("Indice de disciplina invalido: " + indice);
        }
    }
}

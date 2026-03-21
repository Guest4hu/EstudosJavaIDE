package aulas.licoesdeaula.aula27;

public class Professor extends Pessoa {
    private double salario;
    private String curso;

    public Professor(String nome, String endereco, String telefone, String cpf, double salario, String curso) {
        super(nome, endereco, telefone, cpf);
        this.salario = salario;
        this.curso = curso;
    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    public double calcularSalarioLiquido() {
        return 0;
    }
}

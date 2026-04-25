package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex2;

public abstract class Contribuinte {
    private String nome;
    private double saldo;

    public Contribuinte(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void calcularImposto();
}

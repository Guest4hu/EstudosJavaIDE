package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex3;

public class Mamifero extends Animal {
    private static final String HABITAT_NATURAL = "Terra";

    private String alimento;


    public Mamifero(String nome, double comprimento, int numeroDePatas, String cor, double velocidade, String alimento) {
        super(nome, comprimento, numeroDePatas, cor, HABITAT_NATURAL, velocidade);
        this.alimento = alimento;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nAlimento: " + alimento;
    }
}

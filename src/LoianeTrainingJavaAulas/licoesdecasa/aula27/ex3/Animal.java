package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex3;

public abstract class Animal {
    private String nome;
    private double comprimento;
    private int numeroDePatas;
    private String cor;
    private String habitatNatural;
    private double velocidade;

    public Animal(String nome, double comprimento, int numeroDePatas, String cor, String habitatNatural, double velocidade) {
        this.nome = nome;
        this.comprimento = comprimento;
        this.numeroDePatas = numeroDePatas;
        this.cor = cor;
        this.habitatNatural = habitatNatural;
        this.velocidade = velocidade;
    }

    public String toString() {
        return "Animal: " + nome +
                "\nComprimento: " + comprimento + " cm" +
                "\nNúmero de patas: " + numeroDePatas +
                "\nCor: " + cor +
                "\nHabitat natural: " + habitatNatural +
                "\nVelocidade: " + velocidade + " m/s";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public int getNumeroDePatas() {
        return numeroDePatas;
    }

    public void setNumeroDePatas(int numeroDePatas) {
        this.numeroDePatas = numeroDePatas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getHabitatNatural() {
        return habitatNatural;
    }

    public void setHabitatNatural(String habitatNatural) {
        this.habitatNatural = habitatNatural;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }
}

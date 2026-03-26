package aulas.licoesdecasa.aula28_29.ex1;

public abstract class FiguraGeometrica implements Constantes {
    private String nome;
    private String cor;
    private double largura;
    private double altura;

    public FiguraGeometrica(String nome, String cor, double largura, double altura) {
        this.nome = nome;
        this.cor = cor;
        this.largura = largura;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}

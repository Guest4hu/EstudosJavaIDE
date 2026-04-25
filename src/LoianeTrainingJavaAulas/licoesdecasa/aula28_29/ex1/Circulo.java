package LoianeTrainingJavaAulas.licoesdecasa.aula28_29.ex1;

public class Circulo extends Figura2D {
    private static final String NOME = "Circulo";
    private double raio;

    public Circulo(String cor, double largura, double altura) {
        super(NOME, cor, largura, altura);
    }
    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return PI * Math.pow(getRaio(), 2);
    }
}

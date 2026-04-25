package LoianeTrainingJavaAulas.licoesdecasa.aula28_29.ex1;

public class Cubo extends Figura3D {
    private static final String NOME = "Cubo";

    public Cubo(String cor, double largura, double altura) {
        super(NOME, cor, largura, altura);
    }

    @Override
    public double calcularArea() {
        return 6 * Math.pow(getLargura(), 2);
    }

    @Override
    public double calcularVolume() {
        return Math.pow(getLargura(), 3);
    }
}

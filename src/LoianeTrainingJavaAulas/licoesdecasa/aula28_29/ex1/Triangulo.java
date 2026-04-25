package LoianeTrainingJavaAulas.licoesdecasa.aula28_29.ex1;

public class Triangulo extends Figura2D {
    private static final String NOME = "Triangulo";

    public Triangulo(String cor, double largura, double altura) {
        super(NOME, cor, largura, altura);
    }

    @Override
    public double calcularArea() {
        return getLargura() * getAltura() / 2;
    }
}

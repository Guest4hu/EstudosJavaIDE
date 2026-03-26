package aulas.licoesdecasa.aula28_29.ex1;

public class Quadrado extends Figura2D {
    private static final String NOME = "Quadrado";

    public Quadrado( String cor, double largura, double altura) {
        super(NOME, cor, largura, altura);
    }

    @Override
    public double calcularArea() {
        return getLargura() * getAltura();
    }
}

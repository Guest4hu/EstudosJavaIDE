package aulas.licoesdecasa.aula28_29.ex1;

public abstract class Figura2D extends FiguraGeometrica implements DimensaoSuperficial {

    public Figura2D(String nome, String cor, double largura, double altura) {
        super(nome, cor, largura, altura);
    }

    public abstract double calcularArea();

}

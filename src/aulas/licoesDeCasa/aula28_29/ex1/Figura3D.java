package aulas.licoesdecasa.aula28_29.ex1;

public abstract class Figura3D extends FiguraGeometrica implements DimensaoVolumetrica,DimensaoSuperficial {

    public Figura3D(String nome, String cor, double largura, double altura) {
        super(nome, cor, largura, altura);
    }

    public abstract double calcularArea();

    public abstract double calcularVolume();

}

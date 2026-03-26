package aulas.licoesdecasa.aula28_29.ex1;

public class Cilindro extends Figura3D {
    private static final String NOME = "Cilindro";
    private double volume;
    private double raio;

    public Cilindro(String nome, String cor, double largura, double altura) {
        super(nome, cor, largura, altura);
    }

    public static Cilindro criarComVolume(String nome, String cor, double largura, double altura, double volume) {
        Cilindro c = new Cilindro(nome, cor, largura, altura);
        c.volume = volume;
        c.raio = c.calcularRaioComVolume();
        return c;
    }
    public static Cilindro criarComRaio(String nome, String cor, double largura, double altura, double raio) {
        Cilindro c = new Cilindro(nome, cor, largura, altura);
        c.raio = raio;
        c.volume = c.calcularVolume();
        return c;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    @Override
    public double calcularVolume() {
        return PI * Math.pow(raio, 2) * getAltura();
    }
    @Override
    public double calcularArea() {
        return 2 * PI * raio * (raio + getAltura());
    }

    public double calcularRaioComVolume() {
        return PI * Math.pow(raio, 2) * getAltura();
    }
}

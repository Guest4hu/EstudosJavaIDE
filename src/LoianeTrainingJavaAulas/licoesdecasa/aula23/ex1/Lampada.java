package LoianeTrainingJavaAulas.licoesdecasa.aula23.ex1;

public class Lampada {
    private String marca;
    private String modelo;
    private String color;
    private boolean ligado;

    public Lampada(String marca, String modelo, String color, boolean ligado) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.ligado = ligado;
    }

    public Lampada() {

    }
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }
}

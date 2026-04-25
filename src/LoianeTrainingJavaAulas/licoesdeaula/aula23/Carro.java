package LoianeTrainingJavaAulas.licoesdeaula.aula23;

public class Carro {
   private String marca;
   private String modelo;
   private int assentos;
   private double tanqueCombustivel;
   private double consumoCombustivel;

    Carro(String marca, String modelo, int assentos, double tanqueCombustivel, double consumoCombustivel){
        this.marca = marca;
        this.modelo = modelo;
        this.assentos = assentos;
        this.tanqueCombustivel = tanqueCombustivel;
        this.consumoCombustivel = consumoCombustivel;
    }

    public double getConsumoCombustivel() {
        return consumoCombustivel;
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

    public int getAssentos() {
        return assentos;
    }

    public void setAssentos(int assentos) {
        this.assentos = assentos;
    }

    public double getTanqueCombustivel() {
        return tanqueCombustivel;
    }

    public void setTanqueCombustivel(double tanqueCombustivel) {
        this.tanqueCombustivel = tanqueCombustivel;
    }

    public void setConsumoCombustivel(double consumoCombustivel) {
        this.consumoCombustivel = consumoCombustivel;
    }

    void exibirAutonomia(){
        IO.println("Marca: " + marca);
        IO.println("Modelo: " + modelo);
        IO.println("Quantidade de assentos: " + assentos);
        IO.println("Tanque de combustível: " + tanqueCombustivel);
        IO.println("Consumo de combustível: " + consumoCombustivel);
    }
    double obterAutonomia(){
        System.out.println("Metodo autonomia chamado");
        return tanqueCombustivel / consumoCombustivel;
    }

    double calcularCombustivel(double distancia){
        return distancia * consumoCombustivel;
    }

}

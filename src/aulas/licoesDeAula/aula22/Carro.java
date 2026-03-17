package aulas.licoesdeaula.aula22;

public class Carro {
    String marca;
    String modelo;
    int assentos;
    double tanqueCombustivel;
    double consumoCombustivel;

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

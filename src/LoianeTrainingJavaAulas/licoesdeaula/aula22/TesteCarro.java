package LoianeTrainingJavaAulas.licoesdeaula.aula22;

public class TesteCarro {
    public static void main(String[] args) {
        Carro van = new Carro();
        van.marca = "Fiat";
        van.modelo = "Ducato";
        van.assentos = 10;
        van.tanqueCombustivel = 100;
        van.consumoCombustivel = 0.2;

        double autonomia = van.obterAutonomia();
        System.out.println("Autonomia da van: " + autonomia + " km");


        System.out.println("A quantidadre de combustível necessária para percorrer 200 km: " + van.calcularCombustivel(200) + " litros");
        System.out.println("A quantidadre de combustível necessária para percorrer 200 km: " + van.calcularCombustivel(400) + " litros");
        System.out.println("A quantidadre de combustível necessária para percorrer 200 km: " + van.calcularCombustivel(800) + " litros");

    }

}

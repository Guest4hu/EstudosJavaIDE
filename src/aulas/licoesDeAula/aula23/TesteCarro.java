package aulas.licoesdeaula.aula23;

public class TesteCarro {
    public static void main(String[] args) {
        Carro van = new Carro("Fiat", "Ducato", 15, 100, 0.5);


        van.exibirAutonomia();
        double autonomia = van.obterAutonomia();
        System.out.println("Autonomia do carro: " + autonomia + " km");

        double combustivelNecessario = van.calcularCombustivel(100);
        System.out.println("Combustível necessário para percorrer 100 km: " + combustivelNecessario + " litros");
    }

}

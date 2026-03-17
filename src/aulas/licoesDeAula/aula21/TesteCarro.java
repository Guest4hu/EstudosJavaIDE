package aulas.licoesdeaula.aula21;

public class TesteCarro {
    public static void main(String[] args) {
        Carro van = new Carro();
        van.marca = "Fiat";
        van.modelo = "Ducato";
        van.assentos = 10;
        van.tanqueCombustivel = 100;
        van.consumoCombustivel = 0.2;

        IO.println(van);
        IO.println(van.marca);
        IO.println(van.modelo);
        IO.println(van.assentos);
        IO.println(van.tanqueCombustivel);
        IO.println(van.consumoCombustivel);
    }

}

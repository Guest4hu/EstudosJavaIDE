package aulas.licoesdecasa.aula24;

public class TesteContador {
    private static Contador contador = new Contador();
    public static void main(String[] args) {
        System.out.println("Contador inicial: " + Contador.getContador());
        Contador contador1 = new Contador();
        Contador contador2 = new Contador();
        System.out.println("Contador Final: " + Contador.getContador());


    }
}

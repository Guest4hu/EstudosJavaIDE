package aulas.licoesdecasa.aula24;

public class Contador {
    static int contador = 0;

    Contador() {
        contador++;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Contador.contador = contador;
    }

    public static void zerarContador() {
        contador = 0;
    }
}

package aulas.licoesdeaula.aula24;

public class MinhaCalculadora {
    public int somar(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int multiplicar(int a, int b) {
        return a * b;
    }

    public double dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return (double) a / b;
    }

    public double fatorialRecursivo (int n) {
        if (n == 0) {
            return 1;
        }
        return n * fatorialRecursivo(n - 1);
    }

}

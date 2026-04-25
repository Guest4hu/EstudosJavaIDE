package LoianeTrainingJavaAulas.licoesdecasa.aula24;

import java.math.BigInteger;

public class Calculadora {
    public static int somar(int a, int b) {
        return a + b;
    }

    public static int subtrair(int a, int b) {
        return a - b;
    }

    public static int multiplicar(int a, int b) {
        return a * b;
    }

    public static double dividir(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return (double) a / b;
    }

    public static double elevarApotencia(int a, int b) {
        // Sem utilizar o Math.pow, podemos usar um loop para multiplicar a por ele mesmo b vezes
        double resultadoPotencia = 1;
        for (int i = 1; i <= b; i++) {
            resultadoPotencia *= a;
        }
        return resultadoPotencia;
    }

    public static BigInteger fatorial(int a) {
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= a; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }


}

package aulas.licoesdeaula.aula30;

import java.util.Random;

public class TesteMinhaException {

    private static final int TAMANHO_AMOSTRA = 10;
    private static final int LIMITE_ALEATORIO = 100;

    public static void main(String[] args) {
        executarTeste();
    }

    public static void executarTeste() {
        int[] numeradores = gerarValoresAleatorios(TAMANHO_AMOSTRA, LIMITE_ALEATORIO);
        int[] denominadores = gerarValoresAleatorios(TAMANHO_AMOSTRA, LIMITE_ALEATORIO);

        for (int i = 0; i < TAMANHO_AMOSTRA; i++) {
            int numerador = numeradores[i];
            int denominador = denominadores[i];

            try {
                int resultado = dividirSeInteiro(numerador, denominador);
                System.out.printf("%d / %d = %d%n", numerador, denominador, resultado);
            } catch (ArithmeticException | MinhaException e) {
                System.out.println("Erro no indice " + i + ": " + e.getMessage());
            }
        }
    }

    private static int[] gerarValoresAleatorios(int tamanho, int limiteExclusivo) {
        Random random = new Random();
        int[] valores = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            valores[i] = random.nextInt(limiteExclusivo);
        }

        return valores;
    }

    private static int dividirSeInteiro(int numerador, int denominador) {
        if (denominador == 0) {
            throw new ArithmeticException("Divisao por zero nao permitida");
        }

        if (numerador % denominador != 0) {
            throw new MinhaException(numerador, denominador);
        }

        return numerador / denominador;
    }
}

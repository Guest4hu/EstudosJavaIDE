package LoianeTrainingJavaAulas.licoesdeaula.aula30;

public class MinhaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int numerador;
    private final int denominador;

    public MinhaException(int numerador, int denominador) {
        super(String.format("Resultado nao inteiro: %d / %d", numerador, denominador));
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    @Override
    public String toString() {
        return String.format("MinhaException{numerador=%d, denominador=%d, mensagem='%s'}",
                numerador, denominador, getMessage());
    }
}

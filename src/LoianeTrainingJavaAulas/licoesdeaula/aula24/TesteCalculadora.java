package LoianeTrainingJavaAulas.licoesdeaula.aula24;

public class TesteCalculadora {
    public static MinhaCalculadora calculadora = new MinhaCalculadora();
    public static void main(String[] args) {
        calculadora.somar(5, 3);
        calculadora.subtrair(5, 3);
        calculadora.multiplicar(5, 3);
        try {
            calculadora.dividir(5, 1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

package aulas.licoesdecasa.aula24;

import aulas.licoesdeaula.aula24.MinhaCalculadora;

import java.math.BigInteger;

public class TesteCalculadora {
    public static void main(String[] args) {
        System.out.println("Teste da Calculadora");
        double resultadoSoma = Calculadora.somar(5, 3);
        double resultadoSubtrair = Calculadora.subtrair(5, 3);
        double resultadoMultiplicar = Calculadora.multiplicar(5, 3);
        double resultadoDividir = Calculadora.dividir(5, 3);
        double resultadoPotencia = Calculadora.elevarApotencia(5, 3);
        BigInteger resultadoFatorial = Calculadora.fatorial(15);
        System.out.println("Resultado Soma: " + resultadoSoma);
        System.out.println("Resultado Subtrair: " + resultadoSubtrair);
        System.out.println("Resultado Multiplicar: " + resultadoMultiplicar);
        System.out.println("Resultado Dividir: " + resultadoDividir);
        System.out.println("Resultado Potencia: " + resultadoPotencia);
        System.out.println("Resultado Fatorial: " + resultadoFatorial);
    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula31;

public class Teste {
    public static void main(String[] args) {
        Calculadora[] operacoesCalculadora = Calculadora.values();
        for (Calculadora calculadora : operacoesCalculadora) {
            System.out.println(calculadora.name() + ": dos numeros 5 " + calculadora.getOperacao() + " 3 = " + calculadora.executarOperacao(5, 3));
        }


    }
}

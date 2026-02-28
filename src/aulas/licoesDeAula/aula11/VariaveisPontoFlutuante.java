package aulas.licoesDeAula.aula11;

public class VariaveisPontoFlutuante {
    public static void main(String[] args) {
        // Variaveis de ponto flutuante
        float salario01 = 2500.50f; // 32.768 a 32.767
        double salario02 = 2500.50; // -1.79769313486231570E+308 a 1.79769313486231570E+308

        System.out.println("Valor variavel de tipo float: " + salario01);
        System.out.println("Valor variavel de tipo double: " + salario02);
    }
}

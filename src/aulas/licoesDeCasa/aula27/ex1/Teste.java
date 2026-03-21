package aulas.licoesdecasa.aula27.ex1;

public class Teste {
    public static void main(String[] args) {
        ContaBancaria contaEspecial = new ContaEspecial("João", 123456, 1000.0, 500.0);
        ContaPoupanca contaPoupanca = new ContaPoupanca("Pedro", 123456, 1000.0, 0.05);
        double resultadoContaEspecial = contaEspecial.sacar(1200.0);
        System.out.println(resultadoContaEspecial);
        contaPoupanca.calcularNovoSaldo();
        System.out.println(contaPoupanca.toString());
        System.out.println(contaEspecial.toString());
    }
}

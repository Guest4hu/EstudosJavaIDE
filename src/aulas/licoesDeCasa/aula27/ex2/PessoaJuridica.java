package aulas.licoesdecasa.aula27.ex2;


public class PessoaJuridica extends Contribuinte {
    private static final double IMPOSTO_PAGAR_EMPRESA = 0.10;

    public PessoaJuridica(String nome, double saldo) {
        super(nome, saldo);
    }

    public void calcularImposto(){
        double saldo = getSaldo();
        double imposto = IMPOSTO_PAGAR_EMPRESA * saldo;
        setSaldo(saldo - imposto);
    };
}

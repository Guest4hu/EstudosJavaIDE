package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex2;

public class PessoaFisica extends Contribuinte {


    public PessoaFisica(String nome, double saldo) {
        super(nome, saldo);
    }

    public void calcularImposto() {
        double imposto = 0;
        double saldo = getSaldo();
        if (saldo <= 1400) {
            imposto = 0;
        } else if (saldo <= 2100) {
            imposto = (saldo * 0.1) + 100;
        } else if (saldo <= 2800) {
            imposto = (saldo * 0.15) + 270;
        } else if (saldo <= 3600) {
            imposto = (saldo * 0.25) + 500;
        } else {
            imposto = (saldo * 0.3) + 700;
        }
        System.out.println("Imposto: " + imposto);
        setSaldo(saldo - imposto);
    }



}

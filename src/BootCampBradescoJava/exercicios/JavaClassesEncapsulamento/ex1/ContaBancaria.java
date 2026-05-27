package BootCampBradescoJava.exercicios.JavaClassesEncapsulamento.ex1;

public class ContaBancaria {
    private String numeroConta;
    private double saldo;
    private double chequeEspecial;
    private static final double TAXA_CHEQUE_ESPECIAL = 0.2;
    private Pessoa titular;



    public ContaBancaria(Pessoa titular ,String numeroConta, double saldo) {
        if (numeroConta == null || numeroConta.isBlank()) throw new IllegalArgumentException("O número da conta não pode ser nulo ou vazio.");
        if (saldo < 0) throw new IllegalArgumentException("O saldo não pode ser negativo.");
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.chequeEspecial = (saldo < 500) ? 50
                : (saldo * 0.5);
    }

    public void pagarBoleto(double valorBoleto){
        if (valorBoleto <= 0) throw new IllegalArgumentException("O valor do boleto deve ser positivo.");

        sacar(valorBoleto);
    }

    public boolean isUsingChequeEspecial(){
        return this.saldo < 0;
    }

    public double getChequeEspecialUsed(){
        return isUsingChequeEspecial() ? Math.abs(this.saldo) : 0;
    }


    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }


    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void depositar(double valor){
        if (valor <= 0) throw new IllegalArgumentException("Valor de depósito inválido.");

        this.saldo += valor;
    }

    public void sacar(double valor){

        if (valor <= 0) throw new IllegalArgumentException("Valor de saque inválido.");

        if (valor > saldo + chequeEspecial) throw new IllegalArgumentException("Saldo insuficiente.");

        //Aplicando taxa de 20% para cheques especiais
        if (valor > saldo) valor = ((valor - saldo) * (1 + TAXA_CHEQUE_ESPECIAL)) + saldo;

        saldo -= valor;
    }
}

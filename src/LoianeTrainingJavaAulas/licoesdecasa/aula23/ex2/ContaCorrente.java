package LoianeTrainingJavaAulas.licoesdecasa.aula23.ex2;

public class ContaCorrente {
    private double saldo;
    private String titular;
    private String numeroConta;
    private String agencia;
    private double limiteChequeEspecial;
    private boolean especial;

    public ContaCorrente(double saldo, String titular, String numeroConta, String agencia, double limiteChequeEspecial, boolean especial) {
        this.saldo = saldo;
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.limiteChequeEspecial = limiteChequeEspecial;
        this.especial = especial;
    }
    void realizarSaque(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saque realizado com sucesso. Novo saldo: " + saldo);
        } else if (this.especial) {
            if (this.saldo + this.limiteChequeEspecial >= valor) {
                this.saldo -= valor;
                System.out.println("Saque realizado com sucesso utilizando limite especial. Novo saldo: " + saldo);
            } else {
                System.out.println("Saldo insuficiente, mesmo com limite especial.");
            }
        } else {
            System.out.println("Saldo insuficiente e conta não é especial.");
        }
    }

    void realizarDeposito(double valor) {
        this.saldo += valor;
        System.out.println("Depósito realizado com sucesso. Novo saldo: " + saldo);
    }



    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}

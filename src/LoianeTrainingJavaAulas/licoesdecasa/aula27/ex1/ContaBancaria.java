package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex1;

public class ContaBancaria{
    private String nomeCliente;
    private int id;
    private double saldo;


    public ContaBancaria(String nomeCliente, int id, double saldo) {
        this.nomeCliente = nomeCliente;
        this.id = id;
        this.saldo = saldo;
    }

    public String toString() {
        return "ContaBancaria{" +
                "nomeCliente='" + nomeCliente + '\'' +
                ", id=" + id +
                ", saldo=" + saldo +
                '}';
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente para saque.");
        } else {
            saldo -= valor;
        }
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex1;

public class ContaEspecial extends ContaBancaria{
    private double limite;

    public ContaEspecial(String nomeCliente, int id, double saldo, double limite) {
        super(nomeCliente, id, saldo);
        this.limite = limite;
    }

    @Override
    public String toString() {
        return super.toString() + " ContaEspecial{" +
                "ContaEspecial{" +
                "limite=" + limite +
                '}';
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double sacar(double valor) {
        if (valor <= getSaldo() + limite) {
            setSaldo(getSaldo() - valor);
            return valor;
        } else {
            System.out.println("Saldo insuficiente, limite excedido.");
            return 0;
        }
    }



}

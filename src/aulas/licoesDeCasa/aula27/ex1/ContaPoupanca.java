package aulas.licoesdecasa.aula27.ex1;

public class ContaPoupanca extends ContaBancaria {
    private double taxaJuros;

    public ContaPoupanca(String nomeCliente, int id, double saldo, double taxaJuros) {
        super(nomeCliente, id, saldo);
        this.taxaJuros = taxaJuros;
    }

    @Override
    public String toString() {
        return super.toString() + " ContaPoupanca{" +
                "taxaJuros=" + taxaJuros +
                '}';
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public void calcularNovoSaldo() {
        super.setSaldo(super.getSaldo() * (1 + this.taxaJuros));
    }


}

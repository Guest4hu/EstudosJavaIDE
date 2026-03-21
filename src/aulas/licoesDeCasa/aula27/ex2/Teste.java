package aulas.licoesdecasa.aula27.ex2;

public class Teste {
    public static void main(String[] args) {
        Contribuinte empresa = new PessoaJuridica("Empresa XYZ", 250000);
        System.out.println("Saldo antes do imposto: " + empresa.getSaldo());
        empresa.calcularImposto();
        System.out.println("Saldo ao imposto: " + empresa.getSaldo());
        Contribuinte clt = new PessoaFisica("João Silva", 5000);
        System.out.println("Saldo antes do imposto: " + clt.getSaldo());
        clt.calcularImposto();
        System.out.println("Saldo ao imposto: " + clt.getSaldo());
    }
}

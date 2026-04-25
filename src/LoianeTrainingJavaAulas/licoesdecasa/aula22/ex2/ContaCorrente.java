package LoianeTrainingJavaAulas.licoesdecasa.aula22.ex2;

public class ContaCorrente {
    int id;
    String nome;
    double saldo;
    boolean especial;
    double limiteEspecial;

    void realizarSaque(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso. Novo saldo: " + saldo);
        } else if (especial) {
            if (saldo + limiteEspecial >= valor) {
                saldo -= valor;
                System.out.println("Saque realizado com sucesso utilizando limite especial. Novo saldo: " + saldo);
            } else {
                System.out.println("Saldo insuficiente, mesmo com limite especial.");
            }
        } else {
            System.out.println("Saldo insuficiente e conta não é especial.");
        }
    }

    void realizarDeposito(double valor) {
        saldo += valor;
        System.out.println("Depósito realizado com sucesso. Novo saldo: " + saldo);
    }

    double getSaldo() {
        return saldo;
    }


}

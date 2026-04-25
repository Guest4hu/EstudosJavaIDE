package LoianeTrainingJavaAulas.licoesdeaula.aula34;

public class MinhaThread implements Runnable {

    private String nome;

    public MinhaThread() {
    }

    public MinhaThread(String nome) {
        this.nome = nome;
    }

    public Thread criarComaInstancia(String nome) {return new Thread(new MinhaThread(nome));}

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Executando " + nome + " - Iteração: " + (i + 1));
        }
    }

}

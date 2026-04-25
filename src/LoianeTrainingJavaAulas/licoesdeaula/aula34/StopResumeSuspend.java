package LoianeTrainingJavaAulas.licoesdeaula.aula34;

public class StopResumeSuspend implements Runnable {

    private String nome;
    private boolean suspended;

    public StopResumeSuspend(String nome) {
        this.nome = nome;
        this.suspended = false;
        new Thread(this, nome).start();
    }


    @Override
    public void run() {
        System.out.println("Executando thread " + this.nome);

        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.println("Thread " + this.nome + " contador: " + i);
                synchronized (this) {
                    while (suspended) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + this.nome + " finalizada");

    }
}

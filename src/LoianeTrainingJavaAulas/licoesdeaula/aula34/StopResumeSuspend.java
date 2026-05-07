package LoianeTrainingJavaAulas.licoesdeaula.aula34;

public class StopResumeSuspend implements Runnable {

    private String nome;
    private boolean suspended;
    private boolean foiFinalizada;

    public StopResumeSuspend(String nome) {
        this.nome = nome;
        this.suspended = false;
        new Thread(this, nome).start();
    }


    @Override
    synchronized public void run() {
        System.out.println("Executando thread " + this.nome);

        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.println("Thread " + this.nome + " contador: " + i);
                    while (suspended) {
                        wait();
                    }
                    if (foiFinalizada) {
                        break;
                    }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + this.nome + " finalizada");
    }

    public void suspend() {
        this.suspended = true;
    }

    synchronized public void resume() {
        this.suspended = false;
        notify();
    }

    synchronized public void stop(){
        this.foiFinalizada = true;
        notify();
    }
}

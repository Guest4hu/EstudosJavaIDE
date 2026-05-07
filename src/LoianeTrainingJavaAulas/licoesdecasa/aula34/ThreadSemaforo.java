package LoianeTrainingJavaAulas.licoesdecasa.aula34;

public class ThreadSemaforo implements Runnable{
    private String cor;
    private String texto;
    private boolean suspended;
    private boolean foiFinalizada;

    public ThreadSemaforo(String cor,String texto) {
        this.cor = cor;
        this.texto = texto;
        this.suspended = false;
        new Thread(this, cor).start();
    }

    @Override
    public void run() {
        System.out.println("Cor: " + cor);
        while (!foiFinalizada) {
            System.out.println(this.texto);
            synchronized (this) {
                while (suspended) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    synchronized public void suspend() {
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

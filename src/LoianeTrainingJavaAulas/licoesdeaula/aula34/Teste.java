package LoianeTrainingJavaAulas.licoesdeaula.aula34;

public class Teste {
    public static void main(String[] args) {
        testeMinhaThread();
        //testeStopResumeSuspend();
    }

    private static void testeStopResumeSuspend() {
        StopResumeSuspend thread = new StopResumeSuspend("Tread com metodos StopResumeSuspend");

        teste1.start();
        teste2.start();

        try {
            Thread.sleep(2000);
            teste1.suspend();
            System.out.println("Thread 1 pausada");
            Thread.sleep(2000);
            teste1.resume();
            System.out.println("Thread 1 resumida");
            Thread.sleep(2000);
            teste2.stop();
            System.out.println("Thread 2 parada");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static void testeMinhaThread(){
        MinhaThread thread = new MinhaThread();
        Thread teste2 = thread.criarComaInstancia("Hello, World!");
        Thread teste3 = thread.criarComaInstancia("Java é incrível!");
        teste2.start();
        teste3.start();

        try{
            teste3.join();
            teste2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Progama Finalizado");

    }
}

package LoianeTrainingJavaAulas.licoesdeaula.aula34;

public class Deadlock {
    public static void main(String[] args) {
        final String RECURSO_1 = "Recurso #1";
        final String RECURSO_2 = "Recurso #2";

        Thread thread1 = new Thread(() -> {
            synchronized (RECURSO_1) {
                System.out.println("Thread 1: bloqueou o recurso 1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: tentando bloquear o recurso 2");
                synchronized (RECURSO_2) {
                    System.out.println("Thread 1: bloqueou o recurso 2");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (RECURSO_2) {
                System.out.println("Thread 2: bloqueou o recurso 1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: tentando bloquear o recurso 1");
                synchronized (RECURSO_2) {
                    System.out.println("Thread 2: bloqueou o recurso 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

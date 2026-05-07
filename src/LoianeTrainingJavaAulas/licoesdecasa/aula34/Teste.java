package LoianeTrainingJavaAulas.licoesdecasa.aula34;

public class Teste {
    public static void main(String[] args) {
        ThreadSemaforo verde = new ThreadSemaforo("Verde", "Pode Andar");
        ThreadSemaforo amarelo = new ThreadSemaforo("Amarelo", "Cuidado vai Fechar");
        ThreadSemaforo vermelho = new ThreadSemaforo("Vermelho", "Fechado");

        try {
            // Aguarda threads iniciarem
            Thread.sleep(500);
            
            amarelo.suspend();
            vermelho.suspend();
            Thread.sleep(5000);
            
            verde.suspend();
            amarelo.resume();
            Thread.sleep(1000);
            
            amarelo.suspend();
            vermelho.resume();
            Thread.sleep(3000);
            
            vermelho.suspend();
            verde.resume();
            
            Thread.sleep(5000);
            verde.stop();
            amarelo.stop();
            vermelho.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

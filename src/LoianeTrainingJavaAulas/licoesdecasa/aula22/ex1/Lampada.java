package LoianeTrainingJavaAulas.licoesdecasa.aula22.ex1;

public class Lampada {
    String marca;
    String modelo;
    String tipoLuz;
    int potencia;
    boolean statusLigada;

        void ligar() {
            statusLigada = true;
        }
        void desligar() {
            statusLigada = false;
        }
}

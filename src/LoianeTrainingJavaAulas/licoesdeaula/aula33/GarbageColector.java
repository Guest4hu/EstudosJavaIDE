package LoianeTrainingJavaAulas.licoesdeaula.aula33;

import LoianeTrainingJavaAulas.licoesdecasa.aula30.Contato;

public class GarbageColector {
    static void obterMemoria(){
        final int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();

        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = (totalMemory - freeMemory) / mb;
        System.out.println(usedMemory + "MB");
    }

    public static void main(String[] args) {
        Contato[] contatos = new Contato[1000000];
        for (int i = 0; i < contatos.length; i++) {
            contatos[i] = new Contato("Contato " + i, "123456789");
        }

        obterMemoria();

        contatos = null;

        Runtime.getRuntime().gc();

        obterMemoria();

    }
}

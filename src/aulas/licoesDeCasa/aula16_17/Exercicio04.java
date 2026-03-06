package aulas.licoesdecasa.aula16_17;

import aulas.licoesdeaula.aula16.While;

public class Exercicio04 {
    public static void main(String[] args) {
        double paisA = 80000;
        double paisB = 200000;
        int anos = 0;
        while (paisA <= paisB) {
            paisA *= 1.03;
            paisB *= 1.015;
            anos++;
        }
        System.out.println("Paises A: " + paisA);
        System.out.println("Paises B: " + paisB);
        System.out.println("Anos: " + anos);
    }
}

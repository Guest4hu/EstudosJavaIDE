package aulas.licoesdeaula.aula20;

import java.util.Random;

public class ForEach {
    public static void main(String[] args) {
        int[] nota = new int[10];
        Random rand = new Random();
        for (int i = 0; i < nota.length; i++) {
            nota[i] = rand.nextInt(11);
        }
        for (int n : nota) {
            System.out.println(n);
        }
    }
}

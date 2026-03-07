package aulas.licoesdecasa.aula18;

public class Exercicio18 {
    public static void main(String[] args) {
        int[] idade = new int[10];
        int maiorIdade = Integer.MIN_VALUE;
        int menorIdade = Integer.MAX_VALUE;
        int posicaoMaiorIdade = 0;
        int posicaoMenorIdade = 0;

        for (int i = 0; i < idade.length; i++) {
            idade[i] = (int) (Math.random() * 100);
        }

        for (int i = 0; i < idade.length; i++) {
            System.out.println((i + 1) + " - " + idade[i]);
            if (idade[i] > maiorIdade){
                maiorIdade = idade[i];
                posicaoMaiorIdade = i + 1;
            }
            if (idade[i] < menorIdade){
                menorIdade = idade[i];
                posicaoMenorIdade = i + 1;
            }
        }
        System.out.println("Maior idade: " + maiorIdade);
        System.out.println("Posição do maior idade: " + posicaoMaiorIdade );
        System.out.println("Menor idade: " + menorIdade);
        System.out.println("Posição do menor idade: " + posicaoMenorIdade);
    }
}

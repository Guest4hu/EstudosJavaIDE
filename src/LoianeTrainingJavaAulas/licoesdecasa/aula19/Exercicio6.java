package LoianeTrainingJavaAulas.licoesdecasa.aula19;

import java.util.Scanner;

public class Exercicio6 {
    static int[][] jogoDaVelha = new int[3][3];
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Jogo de velha:");
        prencherTabuleiro(jogoDaVelha);
        imprimirTabuleiro(jogoDaVelha);
        int contador = 1;
        System.out.println("`Player 1` é o X e `Player 2` é o número 0");
        while (true) {
            escolhaJogador(contador);
            imprimirTabuleiro(jogoDaVelha);
            if (vencedor()) {
                break;
            }
            contador++;
        }
    }

    public static void prencherTabuleiro(int[][] jogoDaVelha) {
        int contadorParaJogada = 1;
        for (int i = 0; i < jogoDaVelha.length; i++) {
            for (int j = 0; j < jogoDaVelha[i].length; j++) {
                jogoDaVelha[i][j] = contadorParaJogada++;
            }
        }
    }
    public static void imprimirTabuleiro(int[][] jogoDaVelha) {
        int contador = 1;
        for (int i = 0; i < jogoDaVelha.length; i++) {
            for (int j = 0; j < jogoDaVelha[i].length; j++) {
                if (jogoDaVelha[i][j] == 88 || jogoDaVelha[i][j] == 79 ) {
                    System.out.print((char) jogoDaVelha[i][j] + " ");
                    colocarArestas(i, j);
                    continue;
                } else {
                    System.out.print(jogoDaVelha[i][j] + " ");
                    colocarArestas(i, j);
                }
            }
            colocarBarras(i);
            System.out.println();
        }
    }
    public static void colocarBarras(int i) {
        if (i < jogoDaVelha.length - 1) {
            System.out.print("\n---------");
        }
    }


    public static void colocarArestas(int i, int j) {
        if (j < jogoDaVelha[i].length - 1) {
            System.out.print("| ");
        }
    }

    public static int escolhaJogador(int contador) {
        int posicao;
        if (contador % 2 == 1) {
            System.out.print("Player 1, escolha a posicao: ");
        } else {
            System.out.print("Player 2, escolha a posição: ");
        }
        posicao = input.nextInt();
        if (!validarPosicao(posicao)) {
            System.out.println("Posição inválida, tente novamente.");
            return escolhaJogador(contador);
        }
        escolhaPosicao(posicao, contador);
        return posicao;
    }
    public static void escolhaPosicao(int posicao, int contador) {
        int jogada = (contador % 2 == 1) ? 88 : 79; // 88 para X e 79 para O
        int linha = (posicao - 1) / 3;
        int coluna = (posicao - 1) % 3;
        jogoDaVelha[linha][coluna] = jogada;
    }

    public static boolean validarPosicao(int posicao) {
        int linha = (posicao - 1) / 3;
        int coluna = (posicao - 1) % 3;
        if (jogoDaVelha[linha][coluna] == 88 || jogoDaVelha[linha][coluna] == 79) {
            return false;
        }
        return true;
    }
    public  static boolean vencedor() {
        for (int i = 0; i < jogoDaVelha.length; i++) {
            if (jogoDaVelha[i][0] == jogoDaVelha[i][1] && jogoDaVelha[i][1] == jogoDaVelha[i][2]) {
                System.out.println("Jogador " + (jogoDaVelha[i][0] == 88 ? "1" : "2") + " venceu!");
                return true;
            }
        }
        for (int j = 0; j < jogoDaVelha[0].length; j++) {
            if (jogoDaVelha[0][j] == jogoDaVelha[1][j] && jogoDaVelha[1][j] == jogoDaVelha[2][j]) {
                System.out.println("Jogador " + (jogoDaVelha[0][j] == 88 ? "1" : "2") + " venceu!");
                return true;
            }
        }
        if (jogoDaVelha[0][0] == jogoDaVelha[1][1] && jogoDaVelha[1][1] == jogoDaVelha[2][2]) {
            System.out.println("Jogador " + (jogoDaVelha[0][0] == 88 ? "1" : "2") + " venceu!");
            return true;
        }
        if (jogoDaVelha[0][2] == jogoDaVelha[1][1] && jogoDaVelha[1][1] == jogoDaVelha[2][0]) {
            System.out.println("Jogador " + (jogoDaVelha[0][2] == 88 ? "1" : "2") + " venceu!");
            return true;
        }
        return false;
    }

}

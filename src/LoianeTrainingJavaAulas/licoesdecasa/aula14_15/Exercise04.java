package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Scanner;
    class Exercise04 {
        public static void main(String[] args) {
            Scanner ler = new Scanner(System.in);
            System.out.println("Digite uma letra: ");


            String vogais = ler.nextLine().toLowerCase();
            switch (vogais) {
                case "a":
                case "e":
                case "i":
                case "o":
                case "u":
                    System.out.println("A letra digitada é uma vogal.");
                    break;
                default:
                    System.out.println("A letra digitada é uma consoante.");
                    break;
            }
        }
    }

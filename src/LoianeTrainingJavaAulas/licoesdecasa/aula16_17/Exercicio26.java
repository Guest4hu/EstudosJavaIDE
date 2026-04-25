package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String continuar = "s";
        int votosCandidatoA = 0;
        int votosCandidatoB = 0;
        int votosCandidatoC = 0;
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("Por favor me informe, o canditato que deseja votar: ");
            System.out.println("1 - Candidato A");
            System.out.println("2 - Candidato B");
            System.out.println("3 - Candidato C");
            int voto = sc.nextInt();
            boolean votoVerdadeiro = false;
            switch (voto) {
                case 1:
                    votosCandidatoA++;
                    votoVerdadeiro = true;
                    break;
                case 2:
                    votosCandidatoB++;
                    votoVerdadeiro = true;
                    break;
                case 3:
                    votosCandidatoC++;
                    votoVerdadeiro = true;
                    break;
                default:
                    System.out.println("Voto inválido. Por favor, tente novamente.");
                    votoVerdadeiro = false;
            }
            if (!votoVerdadeiro) {
                continue;
            } else {
                System.out.println("Deseja continuar votando? (s/n)");
                continuar = sc.next();
            }
        }
        System.out.println("Votos para o Candidato A: " + votosCandidatoA);
        System.out.println("Votos para o Candidato B: " + votosCandidatoB);
        System.out.println("Votos para o Candidato C: " + votosCandidatoC);
        System.out.print("Total de votos: " + (votosCandidatoA + votosCandidatoB + votosCandidatoC));
        sc.close();
    }
}

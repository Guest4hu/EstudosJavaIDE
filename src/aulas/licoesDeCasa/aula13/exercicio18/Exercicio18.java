package aulas.licoesDeCasa.aula13.exercicio18;
import java.util.Scanner;

public class Exercicio18 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);



        System.out.println("Me informe um arquivo em MB, e a velocidade de download em Mbps: ");
        double tamanhoArquivo = ler.nextDouble();
        double velocidadeDownload = ler.nextDouble();

        double tempoDownload = tamanhoArquivo / velocidadeDownload;

        System.out.println("Tempo de download: " + tempoDownload + " segundos");

        ler.close();
    }
    
}

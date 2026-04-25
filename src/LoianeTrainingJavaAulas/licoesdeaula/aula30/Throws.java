package LoianeTrainingJavaAulas.licoesdeaula.aula30;

import java.util.Scanner;

public class Throws {
    static final Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Digite um numero: ");
        try {
            lerNumero();
        } catch (Exception e) {
            System.out.println("Numero invalido");
            throw new RuntimeException(e);
        }
        finally {
            ler.close();
        }
    }

    public static double lerNumero() throws Exception{
        double numero = ler.nextDouble();
        return numero;
    }
}

package LoianeTrainingJavaAulas.licoesdecasa.aula14_15;

import java.util.Locale;
import java.util.Scanner;

public class Exercise03 {
        public static void main(String[] args) {
            Scanner ler = new Scanner(System.in);

            System.out.println("Me informe seu genero [M/F]: ");
            String genero = ler.nextLine().toUpperCase(Locale.getDefault());
            if (genero.equals("M")) {
                System.out.println("O genero é masculino");
            } else if (genero.equals("F")) {
                System.out.println("O genero é feminino");
            } else {
                System.out.println("Genero invalido");
            }
            ler.close();
        }
}


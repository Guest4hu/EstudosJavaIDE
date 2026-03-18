package aulas.licoesdecasa.aula24;

import java.util.Scanner;

public class TesteConversao {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Qual valor de metros que voce desejs converter para pes?");
        ConversaoDeUnidadesDeArea.setMetro(input.nextInt());
        System.out.println("Valor em metros: " + ConversaoDeUnidadesDeArea.converterMetroParaFeet());
        System.out.println("Qual o valor de Litros que voce deseja converter para centimetros cubicos");
        ConversorUnidadeDeVolumes.setLitro(input.nextInt());
        System.out.println("O valor em Metros Cubicos é: " + ConversorUnidadeDeVolumes.converterLitroParaCentimetroCubico());
        System.out.println("Quantos minutos voce deseja saber em segundos?");
        ConvesaoDeUnidadesDeTempo.setMinutos(input.nextInt());
        System.out.println("O valor é: " + ConvesaoDeUnidadesDeTempo.converterMinutosParaSegundos());
    }
}

package aulas.licoesDeAula.aula11;

public class VariaveisChar {
    public static void main(String[] args){
        char o = 111;
        char i = 105;
        char interrogacao =  0X00E1; // Unicode para o caractere 'á'

        System.out.println("Valor variavel de tipo char: " + o);
        System.out.println("Valor variavel de tipo char: " + i);
        System.out.println(o + i);
        System.out.println("" + o + i + interrogacao);
    }
}

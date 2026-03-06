package aulas.licoesdecasa.aula16_17;

import javax.sound.midi.SysexMessage;
import java.util.Locale;
import java.util.Scanner;

public class Exercicio03 {
    // Faça um programa que leia e valide a idade de uma pessoa. A idade deve ser um número inteiro entre 0 e 150. Se a idade for inválida, o programa deve solicitar que o usuário digite novamente até que uma idade válida seja fornecida,salario,sexo e estado civil.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Digite a idade de uma pessoa: ");
            int idade = Integer.parseInt(input.nextLine());
            if (idade >= 0 && idade <= 150) {
                System.out.println("Idade válida: " + idade);
                break;
            } else {
                System.out.println("Idade inválida. Por favor, digite novamente.");
            }
        }
        while (true){
            System.out.println("Digite o seu nome: ");
            String nomeString = input.nextLine();

            if (nomeString.matches("[a-zA-Z]+") && nomeString.length() > 3) {
                System.out.println("Nome válido: " + nomeString);
                break;
            } else {
                System.out.println("Nome inválido. Por favor, digite novamente.");
            }
        }
        while (true){
            System.out.println("Digite o seu salario: ");
            double salario = Double.parseDouble(input.nextLine());
            if (salario > 0) {
                System.out.println("Salario Valido: " + salario);
                break;
            } else {
                System.out.println("Número inválido. Por favor, digite novamente.");
            }
        }
        while (true){
            System.out.println("Digite o seu sexo: [F/M]: ");
            String sexo = input.nextLine().toUpperCase(Locale.getDefault());
            if (sexo.equals("F") || sexo.equals("M")) {
                System.out.println("Sexo válido: " + sexo);
                break;
            } else {
                System.out.println("Sexo inválido. Por favor, digite novamente.");
            }
        }
        while (true){
            System.out.println("Digite o seu estado civil [S] - Solteiro [C] - Casado [V] - Viuvo - [D]  : ");
            String estadoCivil = input.nextLine().toUpperCase(Locale.getDefault());
            if (estadoCivil.equals("S") || estadoCivil.equals("C") || estadoCivil.equals("V") || estadoCivil.equals("D")) {
                System.out.println("Estado civil válido");
                break;
            } else {
                System.out.println("Estado civil inválido. Por favor, digite novamente.");
            }
        }
        System.out.println("Cadastro Realizado com Sucesso: ");
         input.close();

    }

}

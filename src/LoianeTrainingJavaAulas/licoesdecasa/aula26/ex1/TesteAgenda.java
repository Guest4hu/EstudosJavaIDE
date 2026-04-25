package LoianeTrainingJavaAulas.licoesdecasa.aula26.ex1;

import java.util.Scanner;

public class TesteAgenda {
    static Agenda agenda = new Agenda();
    static Scanner input = new Scanner(System.in);
    static Contato[] contato = new Contato[10];

    public static void main(String[] args) {
        System.out.println("Bem vindo!");
        System.out.println("Sua agenda");
        System.out.println("Maximo de 10 contatos");
        System.out.println("Qual o nome da agenda?");
        agenda.setNome(input.nextLine());
        for (int i = 0; i < contato.length; i++) {
            contato[i] = new Contato(); // cria o objeto antes de usar

            System.out.println("Qual o nome do contato?");
            contato[i].setNome(input.nextLine());

            System.out.println("Qual o email do contato?");
            contato[i].setEmail(input.nextLine());

            System.out.println("Qual o numero do contato?");
            contato[i].setTelefone(input.nextLine());

            agenda.setContatos(contato);

            System.out.println("Contato adicionado com sucesso!");
            System.out.println("Deseja adicionar mais um contato? (s/n)");
            String resposta = input.nextLine(); // melhor manter só nextLine
            if (resposta.equalsIgnoreCase("n")) {
                break;
            }
        }
        System.out.println("Agenda:" + agenda.getNome());
        for (Contato contatosSalvos : agenda.getContatos()) {
            if (contatosSalvos != null) {
                System.out.println("Nome: " + contatosSalvos.getNome());
                System.out.println("Email: " + contatosSalvos.getEmail());
                System.out.println("Telefone: " + contatosSalvos.getTelefone());
            }
        }
    }
}

package aulas.licoesdecasa.aula30;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste {
    private static final int TAMANHO_AGENDA_MAXIMO = 10;
    private static final Scanner LER = new Scanner(System.in);
    private static final Agenda AGENDA = new Agenda(TAMANHO_AGENDA_MAXIMO);


    public static void main(String[] args) {
        int opcao = escolherOpcao();
        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    ContatosAgenda();
                    break;
                case 2:
                    adicionarContatoAgenda();
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
            opcao = escolherOpcao();
        }
    }

    public static void ContatosAgenda() {
        try {
            Contato[] contatos = AGENDA.listarContatos();
            System.out.println("Contatos cadastrados:");
            for (Contato contato : contatos) {
                System.out.println(contato);
            }
        } catch (ContatoNaoExisteException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int escolherOpcao() {
        while (true) {
            System.out.println("Bem vindo a agenda");
            System.out.println("Escolha uma opcao:");
            System.out.println("Sair - 0");
            System.out.println("Listar Contatos - 1");
            System.out.println("Adicionar Contato - 2");

            try {
                int opcao = LER.nextInt();
                LER.nextLine();

                if (opcao >= 0 && opcao <= 2) {
                    return opcao;
                }

                System.out.println("Opcao fora do intervalo. Digite 0, 1 ou 2.");
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida. Digite apenas numeros.");
                LER.nextLine(); // limpa o token invalido do buffer
            }
        }
    }
    public static void adicionarContatoAgenda() {
        System.out.println("Adicionar contato");
        try {
            System.out.print("Nome: ");
            String nome = lerTextoObrigatorio();

            System.out.print("Telefone: ");
            String telefone = lerTextoObrigatorio();

            Contato contato = new Contato(nome, telefone);
            AGENDA.adicionarContato(contato);
            System.out.println("Contato adicionado com sucesso. ID: " + contato.getId());
        } catch (AgendaCheiaExeption | IllegalArgumentException e) {
            System.out.println("Nao foi possivel adicionar contato: " + e.getMessage());
        }

    }

    private static String lerTextoObrigatorio() {
        while (true) {
            String texto = LER.nextLine();
            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }

            System.out.print("Valor invalido. Digite novamente: ");
        }

    }
}

package aulas.licoesdecasa.aula19;

import java.util.Scanner;

public class Exercicio4 {
        static String[][][] agendaMesDataHorario = new String[12][31][24];
        static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {
        int resposta = -1;
        while (resposta != 0) {
            System.out.println("Função de agenda");
            System.out.println("1 - Agendar compromisso");
            System.out.println("2 - Verificar compromisso");
            System.out.println("3 - Alterar compromisso");
            System.out.println("4 - Limpar agenda");
            System.out.println("5 - Exibir agenda");
            System.out.println("0 - Sair");

            resposta = ler.nextInt();
            switch (resposta) {
                case 1:
                    marcarCompromissoNaAgenda();
                    break;
                case 2:
                    verificarCompromissoAgenda();
                    break;
                case 3:
                    alterarCompromissoAgenda();
                    break;
                case 4:
                    limparAgenda();
                    break;
                case 5:
                    exibirAgenda();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    public static String pegarValoresUsuario() {
        System.out.println("Digite o mês (1-12): ");
        int mes = ler.nextInt() - 1;
        System.out.println("Digite o dia (1-31): ");
        int dia = ler.nextInt() - 1;
        System.out.println("Digite a hora (0-23): ");
        int hora = ler.nextInt();
        ler.nextLine(); // Limpar o buffer do scanner
        if (mes < 0 || mes > 11 || dia < 0 || dia > 30 || hora < 0 || hora > 23) {
            System.out.println("Data,Mes ou hora inválida. Tente novamente.");
            return pegarValoresUsuario();
        }
        return mes + "," + dia + "," + hora + ",";
    }


    public static void exibirAgenda(){
        for (int i = 0; i < agendaMesDataHorario.length; i++) {
            for (int j = 0; j < agendaMesDataHorario[i].length; j++) {
                for (int k = 0; k < agendaMesDataHorario[i][j].length; k++) {
                    if (agendaMesDataHorario[i][j][k] != null) {
                        System.out.println("Mês: " + (i + 1) + ", Dia: " + (j + 1) + ", Hora: " + k + " - Compromisso: " + agendaMesDataHorario[i][j][k]);
                    }
                }
            }
        }
    }
    public static void limparAgenda(){
        for (int i = 0; i < agendaMesDataHorario.length; i++) {
            for (int j = 0; j < agendaMesDataHorario[i].length; j++) {
                for (int k = 0; k < agendaMesDataHorario[i][j].length; k++) {
                    agendaMesDataHorario[i][j][k] = null;
                }
            }
        }
        System.out.println("Agenda limpa com sucesso!");
    }
    public static void marcarCompromissoNaAgenda(){
        String dadosUsuario = pegarValoresUsuario();
        String[] partes = dadosUsuario.split(",");
        int mes = Integer.parseInt(partes[0]);
        int dia = Integer.parseInt(partes[1]);
        int hora = Integer.parseInt(partes[2]);
        if (validarData(mes, dia, hora)) {
            System.out.println("Já existe um compromisso agendado para essa data e hora. Tente novamente.");
            marcarCompromissoNaAgenda();
            return;
        }
        System.out.println("Digite o compromisso: ");
        String compromisso = ler.nextLine();
        agendaMesDataHorario[mes][dia][hora] = compromisso;
        System.out.println("Compromisso agendado com sucesso!");
    }
    public  static void verificarCompromissoAgenda(){
        String dadosUsuario = pegarValoresUsuario();
        String[] partes = dadosUsuario.split(",");
        int mes = Integer.parseInt(partes[0]);
        int dia = Integer.parseInt(partes[1]);
        int hora = Integer.parseInt(partes[2]);
        if (mes < 0 || mes > 11 || dia < 0 || dia > 30 || hora < 0 || hora > 23) {
            System.out.println("Data,Mes ou hora inválida. Tente novamente.");
            verificarCompromissoAgenda();
        }
        String compromisso = agendaMesDataHorario[mes][dia][hora];
        if (compromisso != null) {
            System.out.println("Compromisso agendado para essa data e hora: " + compromisso);
        } else {
            System.out.println("Não há compromisso agendado para essa data e hora.");
        }
    }
    public static  void alterarCompromissoAgenda(){
        String dadosUsuario = pegarValoresUsuario();
        String[] partes = dadosUsuario.split(",");
        int mes = Integer.parseInt(partes[0]);
        int dia = Integer.parseInt(partes[1]);
        int hora = Integer.parseInt(partes[2]);
        if (mes < 0 || mes > 11 || dia < 0 || dia > 30 || hora < 0 || hora > 23) {
            System.out.println("Data,Mes ou hora inválida. Tente novamente.");
            alterarCompromissoAgenda();
            return;
        } else if (!validarData(mes, dia, hora)) {
            System.out.println("Não há compromisso agendado para essa data e hora. Tente novamente.");
            alterarCompromissoAgenda();
            return;
        }
        System.out.println("Digite o novo compromisso: ");
        String compromisso = ler.nextLine();
        agendaMesDataHorario[mes][dia][hora] = compromisso;
        System.out.println("Compromisso alterado com sucesso!");
    }


    public static boolean validarData(int mes, int dia, int hora){
        return agendaMesDataHorario[mes][dia][hora] != null;
    }

}

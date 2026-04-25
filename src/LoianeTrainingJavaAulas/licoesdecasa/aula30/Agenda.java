package LoianeTrainingJavaAulas.licoesdecasa.aula30;

import java.util.Arrays;

public class Agenda {
    private final Contato[] contatos;

    public Agenda(int tamanhoMaximo) {
        this.contatos = new Contato[tamanhoMaximo];
    }

    public void adicionarContato(Contato contato) {
        int indiceLivre = obterIndiceLivre();
        if (indiceLivre < 0) {
            throw new AgendaCheiaExeption("Agenda cheia", contatos.length);
        }

        contatos[indiceLivre] = contato;
    }

    public Contato[] listarContatos() {
        if (estaVazia()) {
            throw new ContatoNaoExisteException("Nenhum contato cadastrado");
        }

        return Arrays.stream(contatos)
                .filter(contato -> contato != null)
                .toArray(Contato[]::new);
    }

    public boolean estaVazia() {
        return Arrays.stream(contatos).allMatch(contato -> contato == null);
    }

    private int obterIndiceLivre() {
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] == null) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "contatos=" + Arrays.toString(contatos) +
                '}';
    }
}

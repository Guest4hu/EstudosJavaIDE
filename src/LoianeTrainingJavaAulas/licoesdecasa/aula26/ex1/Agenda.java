package LoianeTrainingJavaAulas.licoesdecasa.aula26.ex1;

public class Agenda {
    private Contato[] contatos;
    private String nome;

    public Agenda(Contato[] contatos, String nome) {
        this.contatos = contatos;
        this.nome = nome;
    }

    public Agenda() {
    }

    public Contato[] getContatos() {
        return contatos;
    }

    public void setContatos(Contato[] contatos) {
        this.contatos = contatos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

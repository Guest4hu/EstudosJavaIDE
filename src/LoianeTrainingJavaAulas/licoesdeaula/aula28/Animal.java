package LoianeTrainingJavaAulas.licoesdeaula.aula28;

public abstract class Animal {
    private String nome;

    public abstract void emitirSom();

    public Animal(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

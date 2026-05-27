package BootCampBradescoJava.exercicios.JavaClassesEncapsulamento.ex1;

public record Pessoa(String nome, int id) {
    public Pessoa(String nome, int id){
        if (nome == null || nome.isBlank() || nome.isEmpty()) throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");

        if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) throw new IllegalArgumentException("O nome deve conter apenas letras e espaços.");

        if (id <= 0) throw new IllegalArgumentException("O ID deve ser um número positivo.");

        this.nome = nome;
        this.id = id;

    }
}

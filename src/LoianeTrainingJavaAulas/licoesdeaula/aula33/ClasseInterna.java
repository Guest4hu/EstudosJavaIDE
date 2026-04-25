package LoianeTrainingJavaAulas.licoesdeaula.aula33;

public class ClasseInterna {
    private String nome = "Classe Interna";

    public void imprimirNome() {
        System.out.println(nome);
    }

    class ClasseInternaAninhada {
        public void imprimirNomeAninhado() {
            System.out.println("Acessando a variável da classe externa: " + nome);
        }
    }

    void metodoQualquer(){
        //Uma classe que so e criada com esoa funcao de ser usada dentro de um metodo, ou seja, so existe dentro do metodo
        class ClasseInternaAninhada{
            public void metodoQualquer(){
                System.out.println("Metodo qualquer classe externa: " + nome);
            }
        }
    }

    void classeInternaAnonima(){
        class ClasseInternaAninhada{}{
            IO.println("Classe interna anonima");
        }
    }


}

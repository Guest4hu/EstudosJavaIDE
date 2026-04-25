package LoianeTrainingJavaAulas.licoesdeaula.aula31;

public class Formulario {
    enum Genero{
        MASCULINO('M'), FEMININO('F'), OUTRO('O');
        private char sexo;
        Genero(char sexo) {
            this.sexo = sexo;
        }
    }

    private Genero genero;
    private String nome;

}

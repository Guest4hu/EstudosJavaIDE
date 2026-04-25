package LoianeTrainingJavaAulas.licoesdeaula.aula33;

public @interface MinhaAnotacao {
    String nome();
    int aula();

    String descricao() default "Fazendo minha primeira anotação";
    
}

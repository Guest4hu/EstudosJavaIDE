package LoianeTrainingJavaAulas.licoesdeaula.aula31;

public enum DiaSemana {
    SEGUNDA(1),
    TERCA(2),
    QUARTA(3),
    QUINTA(4),
    SEXTA(5),
    SABADO(6),
    DOMINGO(7);

    private int dia;

    DiaSemana(int dia) {
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }
}

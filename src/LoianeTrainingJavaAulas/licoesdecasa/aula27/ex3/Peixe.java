package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex3;

public class Peixe extends Animal {
    private static final String HABITAT_NATURAL = "Mar";
    private static final int NUMERO_DE_PATAS_PADRAO = 0;
    private static final String CARACTERISTICA_PADRAO = "Barbatanas e cauda";
    private static final String COR_PADRAO = "Cinzento";


    private String caracteristica;

    public Peixe(String nome, double comprimento, double velocidade, String caracteristica) {
        super(nome, comprimento, NUMERO_DE_PATAS_PADRAO, COR_PADRAO, HABITAT_NATURAL, velocidade);
        this.caracteristica =
                (caracteristica == null || caracteristica.isEmpty())
                        ? CARACTERISTICA_PADRAO
                        : caracteristica + ", " + CARACTERISTICA_PADRAO;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String toString() {
        return super.toString() +
                "\n Característica: " + caracteristica;
    }
}

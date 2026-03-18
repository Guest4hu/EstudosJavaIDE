package aulas.licoesdecasa.aula24;

public class ConversaoDeUnidadesDeArea {


    private static int metro;
    private static int feet;
    private static int milha;
    private static int acre;


    public ConversaoDeUnidadesDeArea(int metro, int feet, int milha, int acre) {
        this.metro = metro;
        this.feet = feet;
        this.milha = milha;
        this.acre = acre;
    }

    public static double converterMetroParaFeet() {
        return metro / 10.76;
    }

    public static double converterFeetParaCentimetros() {
        return feet / 929;
    }
    public static double converterMilhaParaAcres() {
        return milha / 640;
    }
    public static double converterAcreParaFeet() {
        return acre / 929;
    }

    public static int getMetro() {
        return metro;
    }

    public static void setMetro(int metro) {
        ConversaoDeUnidadesDeArea.metro = metro;
    }

    public static int getFeet() {
        return feet;
    }

    public static void setFeet(int feet) {
        ConversaoDeUnidadesDeArea.feet = feet;
    }

    public static int getMilha() {
        return milha;
    }

    public static void setMilha(int milha) {
        ConversaoDeUnidadesDeArea.milha = milha;
    }

    public static int getAcre() {
        return acre;
    }

    public static void setAcre(int acre) {
        ConversaoDeUnidadesDeArea.acre = acre;
    }






}

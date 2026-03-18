package aulas.licoesdecasa.aula24;

public class ConvesaoDeUnidadesDeTempo {

    private static int minutos;
    private static int hora;
    private static int dia;
    private static int semana;
    private static int mes;
    private static int ano;

    public static double converterMinutosParaSegundos() {
        return minutos * 60;
    }
    public static double converterHotaParaMinutos() {
        return hora  * 60;
    }
    public static double converterDiaParaHoras() {
        return dia * 24;
    }
    public static double converterSemanaParaDias() {
        return semana * 7;
    }
    public static double converterAnoParaDias() {
        return ano * 365;
    }


    public static int getMinutos() {
        return minutos;
    }

    public static void setMinutos(int minutos) {
        ConvesaoDeUnidadesDeTempo.minutos = minutos;
    }

    public static int getHora() {
        return hora;
    }

    public static void setHora(int hora) {
        ConvesaoDeUnidadesDeTempo.hora = hora;
    }

    public static int getDia() {
        return dia;
    }

    public static void setDia(int dia) {
        ConvesaoDeUnidadesDeTempo.dia = dia;
    }

    public static int getSemana() {
        return semana;
    }

    public static void setSemana(int semana) {
        ConvesaoDeUnidadesDeTempo.semana = semana;
    }

    public static int getMes() {
        return mes;
    }

    public static void setMes(int mes) {
        ConvesaoDeUnidadesDeTempo.mes = mes;
    }

    public static int getAno() {
        return ano;
    }

    public static void setAno(int ano) {
        ConvesaoDeUnidadesDeTempo.ano = ano;
    }

}

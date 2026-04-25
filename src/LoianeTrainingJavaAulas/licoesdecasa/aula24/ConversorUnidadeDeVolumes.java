package LoianeTrainingJavaAulas.licoesdecasa.aula24;

public class ConversorUnidadeDeVolumes {
    private static double litro;
    private static double metroCubico;
    private static double galaoAmericano;

    public static double converterLitroParaCentimetroCubico(){
        return litro/ 1000;
    }
    public static double converterMetroCubicoParaLitro(){
        return metroCubico / 1000;
    }
    public static double converterMetroCubicoParaPesCubico(){
        return metroCubico / 35.32;
    }
    public static double converterGalaoAmericanoParaPolegadasCubicas(){
        return galaoAmericano / 231;
    }
    public static double converterGalaoAmericanoParaLitro(){
        return galaoAmericano/ 3.785;
    }



    public static double getLitro() {
        return litro;
    }

    public static void setLitro(double litro) {
        ConversorUnidadeDeVolumes.litro = litro;
    }

    public static double getMetroCubico() {
        return metroCubico;
    }

    public static void setMetroCubico(double metroCubico) {
        ConversorUnidadeDeVolumes.metroCubico = metroCubico;
    }

    public static double getGalaoAmericano() {
        return galaoAmericano;
    }

    public static void setGalaoAmericano(double galaoAmericano) {
        ConversorUnidadeDeVolumes.galaoAmericano = galaoAmericano;
    }

}

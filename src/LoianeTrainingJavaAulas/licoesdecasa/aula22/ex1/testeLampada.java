package LoianeTrainingJavaAulas.licoesdecasa.aula22.ex1;

public class testeLampada {
    public static void main() {
        Lampada l = new Lampada();
        l.marca = "Lampada";
        l.modelo = "Lampada";
        l.tipoLuz = "Branca";
        l.potencia = 10;

        IO.println(l);
        IO.println(l.marca);
        IO.println(l.modelo);
        IO.println(l.tipoLuz);
        IO.println(l.potencia);
        System.out.println("Lâmpada ligada? " + l.statusLigada);
            l.ligar();
            System.out.println("Lâmpada ligada? " + l.statusLigada);
            l.desligar();
            System.out.println("Lâmpada ligada? " + l.statusLigada);




    }
}

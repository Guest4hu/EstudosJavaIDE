package LoianeTrainingJavaAulas.licoesdecasa.aula27.ex3;

public class Teste {
    public static void main(String[] args) {
        Animal camelo = new Mamifero("Camelo", 150, 4,"Laranja", 3,"Cacto");
        Animal urso = new Mamifero("Urso", 550, 4,"Marrom", 10,"Mel");
        Animal tubarao = new Peixe("Peixe", 200, 5, null);

        System.out.println(camelo);
        System.out.println(urso);
        System.out.println(tubarao);
    }
}

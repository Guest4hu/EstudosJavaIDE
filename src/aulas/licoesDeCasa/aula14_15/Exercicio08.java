package aulas.licoesdecasa.aula14_15;

import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite me informe o valor dos produtos: ");

        double produto1 = ler.nextDouble();
        double produto2 = ler.nextDouble();
        double produto3 = ler.nextDouble();

        if (produto1 < produto2 && produto1 < produto3) {
            System.out.println("Compre o produto 1, que custa R$ " + produto1);
        } else if (produto2 < produto1 && produto2 < produto3) {
            System.out.println("Compre o produto 2, que custa R$ " + produto2);
        } else if (produto3 < produto1 && produto3 < produto2) {
            System.out.println("Compre o produto 3, que custa R$ " + produto3);
        } else {
            System.out.println("Os produtos tem o me56smo valor, escolha qualquer um deles.");
        }





    }
}

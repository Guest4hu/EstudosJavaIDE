package aulas.licoesdecasa.aula16_17;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio37 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> listaCodigo = new ArrayList<>();
        ArrayList<Integer> listaPeso = new ArrayList<>();
        ArrayList<Double> listaAltura = new ArrayList<>();
        int codigo = 1;
        int peso;
        double altura;
        double maiorAltura = Double.MIN_VALUE;
        int maiorPeso = Integer.MIN_VALUE;
        int codigoClienteMaisAlto = 0;
        int codigoClienteMaisPesado = 0;
        double menorAltura = Double.MAX_VALUE;
        int menorPeso = Integer.MAX_VALUE;
        int codigoClienteMenor = 0;
        int codigoClienteMaisLeve = 0;

        while (codigo != 0){
            System.out.println("Digite o codigo do cliente: ");
            codigo = input.nextInt();
            if (codigo != 0) {
                listaCodigo.add(codigo);
                System.out.println("Digite o peso do cliente: ");
                peso = input.nextInt();
                listaPeso.add(peso);
                System.out.println("Digite a altura do cliente: ");
                altura = input.nextDouble();
                listaAltura.add(altura);
                System.out.println("Cliente Cadastrado Proximo: ");

            } else {
                System.out.println("Encerrando Sistema: ");
                }
            }
        for (int i = 0; i < listaPeso.size(); i++){
            if (listaPeso.get(i) > maiorPeso ){
                maiorPeso = listaPeso.get(i);
                codigoClienteMaisPesado = listaCodigo.get(i);
            } else if (listaPeso.get(i) < menorPeso){
                menorPeso = listaPeso.get(i);
                codigoClienteMaisLeve = listaCodigo.get(i);
            }
            if (listaAltura.get(i) > maiorAltura){
                maiorAltura = listaAltura.get(i).doubleValue();
                codigoClienteMaisAlto = listaCodigo.get(i);
            } else if (listaAltura.get(i) < menorAltura){
                menorAltura = (int) listaAltura.get(i).doubleValue();
                codigoClienteMenor = listaCodigo.get(i);
            }
        }
        System.out.println("O cliente mais alto tem o codigo: " + codigoClienteMaisAlto + " e a altura de: " + maiorAltura);
        System.out.println("O cliente mais baixo tem o codigo: " + codigoClienteMenor + " e a altura de: " + menorAltura);
        System.out.println("O cliente mais pesado tem o codigo: " + codigoClienteMaisPesado + " e o peso de: " + maiorPeso);
        System.out.println("O cliente mais leve tem o codigo: " + codigoClienteMaisLeve + " e o peso de: " + menorPeso);
         input.close();



    }

}

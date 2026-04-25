package LoianeTrainingJavaAulas.licoesdecasa.aula16_17;

import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {
        //Faça um programa que leia um nome de usuario e a sua senha e não aceite a senha igual ao nome do usuario, mostrando uma mensagem de erro e voltando a pedir as informações.
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Digite seu nome: ");
            String nome = input.nextLine();
            System.out.println("Digite sua senha: ");
            String senha = input.nextLine();
            if(nome.equals(senha)){
                System.out.println("Erro: A senha não pode ser igual ao nome do usuário. Por favor, tente novamente.");
            } else {
                System.out.println("Cadastro realizado com sucesso!");
                break;
            }
        }



    }
}

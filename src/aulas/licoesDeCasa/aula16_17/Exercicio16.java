package aulas.licoesdecasa.aula16_17;

public class Exercicio16 {
    public static void main(String[] args) {
        long soma = 0;
        long serieDeFibonacci = 0;
        long proximoNumero = 1;
        while(true){
            System.out.println(serieDeFibonacci);
            soma += serieDeFibonacci;
            if(soma > 500) {
                break;
            }
            long temp = proximoNumero;
            proximoNumero = serieDeFibonacci + proximoNumero;
            serieDeFibonacci = temp;
        }
    }
}

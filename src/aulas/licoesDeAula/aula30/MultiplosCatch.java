package aulas.licoesdeaula.aula30;

public class MultiplosCatch {
    public static void main(String[] args) {
        int[] numeros = {4,8,16,32,64};
        int[] denominadores = {2,0,4,8};

        for (int i = 0; i < numeros.length; i++) {
            try {
                System.out.println("numeros:" + numeros[i] + " denominadores:" + denominadores[i] + " = " + (numeros[i] / denominadores[i]));
//            } catch (Throwable e){
//                System.out.println("Ocorreu um erro: " + e.getMessage());
//            }
            } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro: Divisão por zero. " + e.getMessage());
            }
        }


    }

}

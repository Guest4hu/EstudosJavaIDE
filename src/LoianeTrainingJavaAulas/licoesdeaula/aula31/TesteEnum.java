package LoianeTrainingJavaAulas.licoesdeaula.aula31;

public class TesteEnum {
    public static void main(String[] args) {
        DiaSemana diaDomingo = DiaSemana.DOMINGO;
        System.out.println(diaDomingo.toString() + " - " + diaDomingo.getDia());
        Data data =  new Data(31, 12, 2024,DiaSemana.DOMINGO);

        //Meotdo values transforma em um array os valores;
        DiaSemana[] dia = DiaSemana.values();
        for (DiaSemana diaSemana : dia) {
            System.out.println(diaSemana.toString() + " - " + diaSemana.getDia());
        }

        for (TipoDocumento tipoDocumento : TipoDocumento.values()) {
            System.out.println(tipoDocumento.toString() + " - " + tipoDocumento.geradorNumero());
        }

        Pessoa pessoa = new Pessoa(TipoDocumento.CPF, TipoDocumento.CPF.geradorNumero());
        System.out.println(" - Documento: " + pessoa.getTipoDocumento().toString() + " - Numero: " + pessoa.getTipoDocumento().geradorNumero());

    }
}

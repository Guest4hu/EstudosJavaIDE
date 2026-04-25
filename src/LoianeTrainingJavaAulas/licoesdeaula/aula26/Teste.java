package LoianeTrainingJavaAulas.licoesdeaula.aula26;

public class Teste {
    public static void main(String[] args) {
        Endereco endereco = new Endereco(
                "08110-750",
                "Rua Salvador Balbino de Matos",
                "34",
                "-",
                "Itaim Paulista",
                "São Paulo",
                "São Paulo"
        );
        Telefone[] telefone =  new Telefone[2];
        telefone[0] = new Telefone("celular","964387472","11");
        telefone[1] = new Telefone("fixo","25661508","11");

        Contato contato = new Contato("Gustavo",endereco,telefone);

        System.out.println("Nome: " + contato.getNome());
        System.out.println("Endereço: " + contato.getEndereco().getLogradouro() + ", " + contato.getEndereco().getNumero() + " - " + contato.getEndereco().getBairro() + ", " + contato.getEndereco().getCidade() + " - " + contato.getEndereco().getEstado() + ", CEP: " + contato.getEndereco().getCep());
        System.out.println("Telefones: ");
        for (Telefone t : contato.getTelefones()) {
            System.out.println("Tipo: " + t.getTipo() + ", Número: " + t.getNumero() + ", DDD: " + t.getDd());
        }
    }
}

package aulas.licoesdeaula.aula28;

public class Cachorro extends Mamifero implements AnimalEstimacao,AnimalDomesticado {
    private int tamanho;
    private String raca;


    public Cachorro(String nome, int tamanho, String raca) {
        super(nome);
        this.tamanho = tamanho;
        this.raca = raca;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void amamentar() {

    }

    public void emitirSom() {

    }

    @Override
    public void brincar() {

    }

    @Override
    public void alimentar() {

    }

    @Override
    public void levarVeterinario() {

    }

    @Override
    public void aplicarMedicacao() {

    }
}

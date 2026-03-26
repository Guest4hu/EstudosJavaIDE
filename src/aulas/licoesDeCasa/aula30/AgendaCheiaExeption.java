package aulas.licoesdecasa.aula30;

public class AgendaCheiaExeption extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final int tamanhoAgenda;

    public AgendaCheiaExeption(String message, int tamanhoAgenda) {
        super(message + ". Limite: " + tamanhoAgenda);
        this.tamanhoAgenda = tamanhoAgenda;
    }

    public int getTamanhoAgenda() {
        return tamanhoAgenda;
    }
}

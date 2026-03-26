package aulas.licoesdecasa.aula30;

public class ContatoNaoExisteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ContatoNaoExisteException(String message) {
        super(message);
    }
}

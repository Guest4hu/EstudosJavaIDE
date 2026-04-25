package LoianeTrainingJavaAulas.licoesdeaula.aula26;

public class Telefone {
    private String tipo;
    private String numero;
    private String dd;

    public Telefone(String tipo, String numero, String dd) {
        this.tipo = tipo;
        this.numero = numero;
        this.dd = dd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }
}

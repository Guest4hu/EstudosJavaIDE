package LoianeTrainingJavaAulas.licoesdecasa.aula31;

public enum Calculadora {
    SOMA('+') {
        @Override
        public double executarOperacao(double num1, double num2) {
            return num1 + num2;
        }
    },
    SUBTRAIR('-') {
        @Override
        public double executarOperacao(double num1, double num2) {
            return num1 - num2 ;
        }
    },
    MULTIPLICAR('*') {
        @Override
        public double executarOperacao(double num1, double num2) {
            return num1 * num2;
        }
    },
    DIVIDIR('/') {
        @Override
        public double executarOperacao(double num1, double num2) {
            return num1 / num2;
        }
    },
    ;
    private char operacao;

    Calculadora(char operacao) {
        this.operacao = operacao;
    }
    public char getOperacao() {
        return operacao;
    }

    @Override
    public String toString() {
        return " " + operacao;
    }

    public abstract double executarOperacao(double x, double y);
}

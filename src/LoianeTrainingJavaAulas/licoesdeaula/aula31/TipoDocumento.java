package LoianeTrainingJavaAulas.licoesdeaula.aula31;




public enum TipoDocumento {
    CPF {
        @Override
        public String geradorNumero() {
            return geraCpfCnpj.cpf();
        }
    },
    CNPJ {
        @Override
        public String geradorNumero() {
            return geraCpfCnpj.cnpj();
        }
    };

    public abstract String geradorNumero();
    private static final GeraCpfCnpj geraCpfCnpj = new GeraCpfCnpj();
}

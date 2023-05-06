package br.com.alura.screenmatch.excecao;

public class AnoInvalidoException extends RuntimeException {
    private final String mensagem;

    public AnoInvalidoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}

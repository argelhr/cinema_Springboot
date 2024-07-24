package br.edu.ifsul.cstsi.cinema.api.infra;

public class TokenInvalidoException extends RuntimeException{
    public TokenInvalidoException(String mensagem) {
        super(mensagem);
    }
}

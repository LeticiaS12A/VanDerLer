package com.tagivan.vandeler.exception;

public class AuthenticationException extends RuntimeException {
	
    public AuthenticationException() {
        super("Falha na autenticação. Usuário ou senha inválidos.");
    }

    public AuthenticationException(String mensagem) {
        super(mensagem);
    }
}
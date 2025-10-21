package com.tagivan.vandeler.exception;

public class NoResultsException extends RuntimeException {
	
	private final String operation;

    public NoResultsException(){
        super("Sem resultados.");
        this.operation = null;
    }

    public NoResultsException(String operation) {
        super(String.format("Entidade não encontrada para %s.", operation));
        this.operation = operation;
    }

    public String getOperacao() {
        return operation;
    }
}
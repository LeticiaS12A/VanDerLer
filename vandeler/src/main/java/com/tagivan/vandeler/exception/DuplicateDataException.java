package com.tagivan.vandeler.exception;

public class DuplicateDataException extends RuntimeException {
	private final String dado;

    public DuplicateDataException() {
        super("Dado(s) já existente(s).");
        this.dado = null;
    }

    public DuplicateDataException (String dado) {
        super(String.format("%s já existente.",  dado));
        this.dado = dado;
    }

    public String getDado() {
        return dado;
    }
}
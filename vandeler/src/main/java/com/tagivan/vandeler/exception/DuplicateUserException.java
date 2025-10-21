package com.tagivan.vandeler.exception;

public class DuplicateUserException extends RuntimeException {
	
	public DuplicateUserException(){
        super("Email ou telefone já existentes.");
    }
	
	public DuplicateUserException(String message) {
        super(message);
    }
}
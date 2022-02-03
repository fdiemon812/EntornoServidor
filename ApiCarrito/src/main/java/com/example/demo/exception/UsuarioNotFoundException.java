package com.example.demo.exception;

public class UsuarioNotFoundException extends RuntimeException{
	
	public UsuarioNotFoundException(String id) {
		super("No hay usuario con ID: " + id);
	}

}

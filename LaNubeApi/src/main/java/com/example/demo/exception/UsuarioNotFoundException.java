package com.example.demo.exception;

public class UsuarioNotFoundException extends RuntimeException{
	
	public UsuarioNotFoundException(String id) {
		super("No existe usuario con ID: " + id);
	}

}
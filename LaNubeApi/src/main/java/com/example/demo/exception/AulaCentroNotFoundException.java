package com.example.demo.exception;

public class AulaCentroNotFoundException extends RuntimeException{
	
	public  AulaCentroNotFoundException(String id) {
		super("En el centro no existe aula con ID: " + id);
	}

}
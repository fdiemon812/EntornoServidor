package com.example.demo.exception;

public class AulaNotFoundException extends RuntimeException{
	
	public  AulaNotFoundException(String id) {
		super("No existe aula con ID: " + id);
	}

}
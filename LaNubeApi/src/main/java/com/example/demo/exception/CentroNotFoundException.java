package com.example.demo.exception;

public class CentroNotFoundException extends RuntimeException{
	
	public  CentroNotFoundException(String id) {
		super("No existe Centro con ID: " + id);
	}

}
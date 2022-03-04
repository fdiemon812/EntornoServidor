package com.example.demo.exception;


/**
 * Gestiona un centro no encontrado
 * @author estudiante
 *
 */
public class CentroNotFoundException extends RuntimeException{
	
	public  CentroNotFoundException(String id) {
		super("No existe Centro con ID: " + id);
	}

}
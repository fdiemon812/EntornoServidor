package com.example.demo.exception;


/**
 * GEstiona un aula no encontrada
 * @author estudiante
 *
 */
public class AulaNotFoundException extends RuntimeException{
	
	public  AulaNotFoundException(String id) {
		super("No existe aula con ID: " + id);
	}

}
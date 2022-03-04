package com.example.demo.exception;


/**
 * GEstiona un aula no encontrada en el centro
 * @author estudiante
 *
 */
public class AulaCentroNotFoundException extends RuntimeException{
	
	public  AulaCentroNotFoundException(String id) {
		super("En el centro no existe aula con ID: " + id);
	}

}
package com.example.demo.exception;

/**
 * Gestiona si un alumno no es encontrado
 * @author estudiante
 *
 */
public class AlumnoNotFoundException extends RuntimeException{
	
	public AlumnoNotFoundException(String id) {
		super("No existe alumno con ID: " + id);
	}

}
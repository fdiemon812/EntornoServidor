package com.example.demo.exception;

/**
 * Gestiona si un alumno no es encontrado
 * @author estudiante
 *
 */
public class TutorNotFoundException extends RuntimeException{
	
	public TutorNotFoundException(int id) {
		super("No existe Tutor con ID: " + id);
	}

}
package com.example.demo.exception;

/**
 * Gestiona si un alumno no tiene datos básicos
 * @author estudiante
 *
 */
public class AlumnoIncompletoException extends RuntimeException{
	
	public AlumnoIncompletoException() {
		super("El alumno debe tener nombre y apellidos ");
	}

}
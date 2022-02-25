package com.example.demo.exception;

public class AlumnoIncompletoException extends RuntimeException{
	
	public AlumnoIncompletoException() {
		super("El alumno debe tener nombre y apellidos ");
	}

}
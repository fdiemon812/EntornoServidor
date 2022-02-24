package com.example.demo.exception;

public class AlumnoNotFoundException extends RuntimeException{
	
	public AlumnoNotFoundException(String id) {
		super("No existe alumno con ID: " + id);
	}

}
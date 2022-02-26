package com.example.demo.exception;

public class AlumnoCentroNotFoundException extends RuntimeException{
	
	public AlumnoCentroNotFoundException(int idAlumno) {
		super("No existe en el centro alumno con id: "+idAlumno);
	}

}
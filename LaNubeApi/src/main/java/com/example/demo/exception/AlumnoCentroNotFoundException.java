package com.example.demo.exception;


/**
 * Gestiona si no existe un alumno en un centro
 * @author estudiante
 *
 */
public class AlumnoCentroNotFoundException extends RuntimeException{
	
	public AlumnoCentroNotFoundException(int idAlumno) {
		super("No existe en el centro alumno con id: "+idAlumno);
	}

}
package com.example.demo.exception;


/**
 * Gestiona la comida introducida al crear alumno
 * @author estudiante
 *
 */
public class ComidaInvalidException extends RuntimeException{
	
	public  ComidaInvalidException() {
		super("La comida debe ser uno de los siguientes: Biberón, Comida , Comida + Biberón");
	}

}
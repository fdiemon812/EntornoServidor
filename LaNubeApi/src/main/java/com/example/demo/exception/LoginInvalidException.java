package com.example.demo.exception;


/**
 * Gestiona la comida introducida al crear alumno
 * @author estudiante
 *
 */
public class LoginInvalidException extends RuntimeException{
	
	public LoginInvalidException() {
		super("Usuario o contraseña incorrecto");
	}

}
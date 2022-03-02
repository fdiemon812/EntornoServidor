package com.example.demo.exception;

public class ComidaInvalidException extends RuntimeException{
	
	public  ComidaInvalidException() {
		super("La comida debe ser uno de los siguientes: Biberón, Comida , Comida + Biberón");
	}

}
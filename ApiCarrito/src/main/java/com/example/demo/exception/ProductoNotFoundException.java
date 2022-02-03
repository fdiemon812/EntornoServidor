package com.example.demo.exception;

public class ProductoNotFoundException extends RuntimeException{
	
	public ProductoNotFoundException(int id) {
		super("No existe el producto : "+id);
	}
	
	

}

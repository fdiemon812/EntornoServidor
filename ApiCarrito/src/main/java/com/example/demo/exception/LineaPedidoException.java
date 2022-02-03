package com.example.demo.exception;

public class LineaPedidoException extends RuntimeException {
	
	
	public LineaPedidoException(int id) {
		super("No existe la linea de pedido con : "+id);
	}
	
	

}
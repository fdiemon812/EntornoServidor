package com.example.demo.exception;

public class PedidoNotFoundException extends RuntimeException{

	
public PedidoNotFoundException(int id) {
	super("No existe el pedido con id: "+id);
}

}

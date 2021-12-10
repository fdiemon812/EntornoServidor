package com.example.demo.model;

import java.util.ArrayList;
import java.util.Objects;

import javax.annotation.PostConstruct;

public class Pedido {
	
	
	public ArrayList<Producto> listaProductos;

	
	public Pedido() {
		
		this.listaProductos = new ArrayList<Producto>();
		

	}

	
	


	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}


	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}


	@Override
	public String toString() {
		return "Pedido [listaProductos=" + listaProductos + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(listaProductos);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(listaProductos, other.listaProductos);
	}
	
	

	
	
	
}


package com.example.demo.model;

import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;



public class Pedido {
	
	
	public HashMap<Producto,Integer> listaProductos;

	
	public Pedido() {
		
		this.listaProductos = new  HashMap<Producto,Integer>();
		

	}

	
	


	public  HashMap<Producto,Integer> getListaProductos() {
		return listaProductos;
	}


	public void setListaProductos( HashMap<Producto,Integer> listaProductos) {
		this.listaProductos = listaProductos;
	}


	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(Map.Entry<Producto, Integer> producto: listaProductos.entrySet()) {
			
			sb.append(producto.getKey() + " - "+ producto.getValue());
		}
		
		return sb.toString();
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


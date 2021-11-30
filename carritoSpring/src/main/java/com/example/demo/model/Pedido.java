package com.example.demo.model;

import java.util.ArrayList;
import java.util.Objects;

public class Pedido {
	
	private Usuario usuario;
	private ArrayList<Producto> listaProductos;

	
	public Pedido() {
		
		this.listaProductos = new ArrayList<Producto>();
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}


	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}


	@Override
	public String toString() {
		return "Pedido [usuario=" + usuario + ", listaProductos=" + listaProductos + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(listaProductos, usuario);
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
		return Objects.equals(listaProductos, other.listaProductos) && Objects.equals(usuario, other.usuario);
	}
	
	

	
	
	
}


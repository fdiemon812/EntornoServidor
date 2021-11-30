package com.example.demo.model;

import java.util.Objects;

public class Producto {
	
	
	private String nombre;
	private int id;
	private Double precio;
	
	
	
	public Producto() {
		
		
	}
	
	public Producto(String nombre, int id, Double precio) {
		
		this.id= id;
		this.nombre=nombre;
		this.precio=precio;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", id=" + id + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}
	
	

}

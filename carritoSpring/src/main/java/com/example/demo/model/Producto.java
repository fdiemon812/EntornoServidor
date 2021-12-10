package com.example.demo.model;

import java.util.Objects;

public class Producto {
	
	
	private String nombre;
	private Double precio;
	private int id;
	private int cantidad;
	private String img;
	
	
	
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto() {
		
		
	}
	
	public Producto(int id) {
		this.id=id;
		
	}
	
	public Producto(String nombre, Double precio, int id, String img) {
		
		this.nombre=nombre;
		this.precio=precio;
		this.id=id;
		this.img=img;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + "]";
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

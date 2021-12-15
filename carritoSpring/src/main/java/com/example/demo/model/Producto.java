package com.example.demo.model;

import java.util.Objects;

import javax.validation.constraints.Min;

public class Producto {
	
	
	private String nombre;
	private Double precio;
	private int id;
	@Min(1)
	private int cantidad;
	private String img;
	
	
	
	/**
	 *  Devuelve la cantidad de productos
	 * @return int cantidad 
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Modifica la cantidad
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	
	/**
	 * Constructor vacio
	 */
	public Producto() {
		
		
	}
	
	/**
	 * Constructor con solo la id
	 * @param id
	 */
	public Producto(int id) {
		this.id=id;
		
	}
	
	/**
	 * Constructor con parametros
	 * @param nombre
	 * @param precio
	 * @param id
	 * @param img
	 */
	public Producto(String nombre, Double precio, int id, String img) {
		
		this.nombre=nombre;
		this.precio=precio;
		this.id=id;
		this.img=img;
	}

	
	/**
	 * Devuelve el nombre del producto
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
/**
 * Recibe el nombre del producto y lo modifica. 
 * @param nombre
 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
/**
 * Devuelve el precio del producto
 * @return
 */
	public Double getPrecio() {
		return precio;
	}

/**
 * 
 * Modifica el precio de un producto
 * @param precio
 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	/**
	 * Obtener ID del producto
	 * @return
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * 
	 * Modificar ID del producto
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * 
	 * Obtener imagen de producto
	 * @return
	 */
	public String getImg() {
		return img;
	}

	
	/**
	 * Modificar imagen de producto
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}

	
	
	/*
	 * 
	 * Devuelve el codigo hash de un producto
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, precio);
	}
	
	/**
	 * 
	 * Recibe un obejto, devuelve true si es igual al producto
	 */
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

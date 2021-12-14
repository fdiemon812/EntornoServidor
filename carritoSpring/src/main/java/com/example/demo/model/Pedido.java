package com.example.demo.model;

import java.beans.JavaBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;



public class Pedido {
	
	
	public HashMap<Producto,Integer> listaProductos;
	private static int contador=0;
	private int id;
	private Date fecha;
	private int precioEnvio;
	private Double totalPedido;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String mail;
	private int tlf;
	

	
	public Pedido() {
		
		this.listaProductos = new  HashMap<Producto,Integer>();
		this.id=contador;
		contador++;
		this.fecha=new Date();
		
		
	}
	
	
	
	
	
	public Pedido(int id) {
		this.id=id;
	
	}




	public Double getTotalPedido() {
		return totalPedido;
	}





	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}









	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getFecha() {
		
		
		Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 
		 
        
        String strDateFormat = "dd-MM-yy"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); 
		
		
		return objSDF.format(objDate);
	}









	public int getPrecioEnvio() {
		return precioEnvio;
	}
	
	
	
	
	
	public void setPrecioEnvio(int precioEnvio) {
		this.precioEnvio = precioEnvio;
	}


	public  HashMap<Producto,Integer> getListaProductos() {
		return listaProductos;
	}


	public void setListaProductos( HashMap<Producto,Integer> listaProductos) {
		this.listaProductos = listaProductos;
	}


	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getApellidos() {
		return apellidos;
	}





	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}





	public String getDireccion() {
		return direccion;
	}





	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}





	public String getMail() {
		return mail;
	}





	public void setMail(String mail) {
		this.mail = mail;
	}





	public int getTlf() {
		return tlf;
	}





	public void setTlf(int tlf) {
		this.tlf = tlf;
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
		return Objects.hash(id);
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
		return id == other.id;
	}


	

	
	
	
}


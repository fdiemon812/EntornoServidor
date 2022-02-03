package com.example.demo.model;

import javax.persistence.Column;

public class DatosUsuarioPedido {
	
	
	private String nombre;
	private String apellidos;
	private String direccion;
	private String mail;
	private String tlf;
	
	
	
	public DatosUsuarioPedido(String nombre, String apellidos, String direccion, String mail, String tlf) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.mail = mail;
		this.tlf = tlf;
	}



	public DatosUsuarioPedido() {
		
		
		
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



	public String getTlf() {
		return tlf;
	}



	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	
	
	
	

}

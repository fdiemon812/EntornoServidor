package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Usuario {
	
	private String user;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String mail;
	private int tlf;
	private String password;
	private ArrayList<Pedido> listaPedidos;
	
	public Usuario() {
		
	}
	
	public Usuario(String user, String password ) {
		this.user=user;
		this.password=password;
	}

	public Usuario(String user, String nombre, String apellidos,
			String password, String direccion, int tlf, String mail) {
		
	super();
	this.user=user;
	this.direccion=direccion;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.password = password;
	this.mail=mail;
	this.tlf=tlf;
	this.listaPedidos=new ArrayList<Pedido>();
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void addListaPedidos(Pedido pedido) {
		listaPedidos.add(0, pedido);;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(user, other.user);
	}
	
	
	
}

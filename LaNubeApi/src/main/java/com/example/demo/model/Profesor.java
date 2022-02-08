package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profesor  extends Usuario{

	private String rol;

	public Profesor() {
		super();
	}
	
	
	public Profesor(String nombre, String apellidos, String dni, String email, String tlf, String password) {
		super(nombre, apellidos, dni, email, tlf, password);
		this.rol="Profesor";
		
	}
	
	
	public Profesor(String nombre, String apellidos, String dni, String email, String tlf, String password, String rol) {
		super(nombre, apellidos, dni, email, tlf, password);
		this.rol=rol;
		
	}
	
}

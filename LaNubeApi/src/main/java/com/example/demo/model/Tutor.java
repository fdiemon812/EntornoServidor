package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.OneToMany;

@Entity
public class Tutor extends Usuario {
	
	
	
	@OneToMany
	private List<Alumno> alumnos = new ArrayList<>();
	
	private String rol;

	
	public Tutor() {
		super();
	}
	
	public Tutor(int id) {
		super();
	}
	
	public Tutor(String nombre, String apellidos, String dni, String email, String tlf, String password) {
		super(nombre, apellidos, dni, email, tlf, password);
		this.rol="Tutor";
		
	}
	
}

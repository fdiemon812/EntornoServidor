package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.OneToMany;

@Entity
public class Tutor extends Usuario {
	
	
	
	@OneToMany
	private List<Alumno> alumnos = new ArrayList<>();
	

	
	public Tutor() {
		super();
	}
	
	public Tutor(Usuario usuario) {
		super.setEmail(usuario.getEmail());
		super.setPassword(usuario.getPassword());
		super.setRole(usuario.getRole());
	}
	
	public Tutor(int id) {
		super();
	}
	
	
	
}

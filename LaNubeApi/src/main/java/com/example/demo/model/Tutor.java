package com.example.demo.model;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Clase tutor. Es un tipo de usuario
 * @author estudiante
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Tutor extends Usuario {
	
	
	
	@ManyToMany
	private List<Alumno> alumnos = new ArrayList<>();
	

	
	public Tutor() {
		super();
	}
	
	public Tutor(Usuario usuario) {
		super.setEmail(usuario.getEmail());
		super.setPassword(usuario.getPassword());
		super.setRole(usuario.getRole());
		super.setApellidos(usuario.getApellidos());
		super.setDni(usuario.getDni());
		super.setTlf(usuario.getTlf());
		super.setNombre(usuario.getNombre());
		
	}
	
	public Tutor(int id) {
		super();
	}
	
	
	public Tutor(String email) {
		super();
		super.setEmail(email);
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	
	
}

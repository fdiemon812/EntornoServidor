package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profesor  extends Usuario{


	public Profesor() {
		super();
	}
	
	public Profesor(Usuario usuario) {
		super.setEmail(usuario.getEmail());
		super.setPassword(usuario.getPassword());
		super.setRole(usuario.getRole());
		super.setApellidos(usuario.getApellidos());
		super.setDni(usuario.getDni());
		super.setTlf(usuario.getTlf());
		super.setNombre(usuario.getNombre());
		
	}
	
	
	public Profesor(String nombre, String apellidos, String dni, String email, String tlf, String password, String role) {
		super(nombre, apellidos, dni, email, tlf, password, role);
		
	}
	
	
	
}

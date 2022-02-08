package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tutor {

	
	public String nombre;
	public String apellidos;
	public String dni;
	public String email;
	public String tlf;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public int id;
	
	@OneToMany
	public List<Alumno> alumnos;
}

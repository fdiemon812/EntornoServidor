package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno {

	public String nombre;
	
	public String apellidos;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public int id;
	public String dni;
	
	@OneToMany
	public List<Tutor> tutor;
	
	
	
}

package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno {

	private String nombre;
	
	private String apellidos;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String dni;
	
	@OneToMany
	private List<Tutor> tutores;
	
	@OneToMany
	private List<EstadoAlumno> estados;
	
	
	
}

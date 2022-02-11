package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Alumno {

	private String nombre;
	
	private String apellidos;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String dni;
	
	@OneToOne
	private Aula aula;
	
	@OneToMany
	private List<Tutor> tutores;
	
	@OneToMany
	private List<EstadoAlumno> estados;
	
	public Alumno() {
		
	}

	public Alumno(String nombre, String apellidos, String dni) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.tutores = new ArrayList<>();
		this.estados = new ArrayList<>();
		
	}
	
	
	
}

package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Clase aula
 * @author estudiante
 *
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aula {
	
	private String nombre;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	@JsonIgnore
	@OneToMany
	private List<Profesor> profesores;
	
	
	@JsonIgnore
	@OneToMany
	private List<Alumno> alumnos;
	
	public Aula() {
		
	}
	
	public Aula(int id) {
		this.id=id;
	}
	
	public Aula(String nombre) {
		super();
		this.nombre = nombre;
		this.profesores = new ArrayList<Profesor>();
		this.alumnos = new ArrayList<Alumno>();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Profesor> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return id == other.id;
	}
	
	

}

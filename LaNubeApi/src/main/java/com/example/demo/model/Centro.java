package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Centro {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	private String nombre;
	private String direccion;
	private String tlf;
	private String email;
	
	@JsonIgnore
	@OneToMany
	private List<Aula> aulas;
	
	@JsonIgnore
	@OneToMany
	private List<Alumno> alumnos;

	public Centro() {};
	
	public Centro(String nombre, String direccion, String tlf, String email, ArrayList<Aula> aulas) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.tlf = tlf;
		this.email = email;
		this.aulas = aulas;
		this.alumnos= new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Aula> getAulas() {
		return aulas;
	}
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
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
		Centro other = (Centro) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Centro [nombre=" + nombre + ", direccion=" + direccion + ", tlf=" + tlf + ", email=" + email
				+ ", aulas=" + aulas + "]";
	}
	

}

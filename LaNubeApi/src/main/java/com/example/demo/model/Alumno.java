package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Alumno {

	private String nombre;
	
	private String apellidos;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String dni;
	private String direccion;
	private Date fechaNacimiento;
	private LocalDateTime horaEntrada;
	private LocalDateTime horaSalida;
	private String comida;
	private boolean comeEnCentro;
	private String observaciones;
	
	@ManyToOne
	private Aula aula;
	
	@JsonIgnore
	@OneToMany
	private List<Tutor> tutores;
	
	@JsonIgnore
	@OneToMany
	private List<EstadoAlumno> estados;
	
	public Alumno(String nombre, String apellidos, String dni, String direccion, Date fechaNacimiento,
			LocalDateTime horaEntrada, LocalDateTime horaSalida, String comida, boolean comeEnCentro,
			String observaciones, Aula aula) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.comida = comida;
		this.comeEnCentro = comeEnCentro;
		this.observaciones = observaciones;
		this.aula = aula;
		this.tutores=new ArrayList<Tutor>();
		this.estados=new ArrayList<EstadoAlumno>();

	}
	
	





	public Alumno(String nombre, String apellidos, String dni) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.tutores=new ArrayList<Tutor>();
		this.estados=new ArrayList<EstadoAlumno>();
	}







	public Alumno() {
		this.tutores=new ArrayList<Tutor>();
		this.estados=new ArrayList<EstadoAlumno>();
	}

	

	

	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getApellidos() {
		return apellidos;
	}





	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getDni() {
		return dni;
	}





	public void setDni(String dni) {
		this.dni = dni;
	}





	public String getDireccion() {
		return direccion;
	}





	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}





	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}





	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}





	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}





	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}





	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}





	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}





	public String getComida() {
		return comida;
	}





	public void setComida(String comida) {
		this.comida = comida;
	}





	public boolean isComeEnCentro() {
		return comeEnCentro;
	}





	public void setComeEnCentro(boolean comeEnCentro) {
		this.comeEnCentro = comeEnCentro;
	}





	public String getObservaciones() {
		return observaciones;
	}





	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}





	public Aula getAula() {
		return aula;
	}





	public void setAula(Aula aula) {
		this.aula = aula;
	}





	public List<Tutor> getTutores() {
		return tutores;
	}





	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}





	public List<EstadoAlumno> getEstados() {
		return estados;
	}





	public void setEstados(List<EstadoAlumno> estados) {
		this.estados = estados;
	}





	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
	
}

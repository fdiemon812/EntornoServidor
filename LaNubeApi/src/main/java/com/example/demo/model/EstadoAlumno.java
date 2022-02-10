package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EstadoAlumno {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	
	private String bath1;
	private String bath2;
	private String bath3;
	private String eat1;
	private String eat2;
	private String eat3;
	private Date horaEntrada;
	private Date horaSalida;
	private boolean asistencia;
	private Date Fecha;

}

package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;


@Service
public class AulaService {

	@Autowired
	private AulaRepo aulaRepo;
	@Autowired
	private AlumnoRepo alumnoRepo;
	
	
	public boolean addAlumno(int idAula, int idAlumno) {

		
		Aula aula = aulaRepo.getById(idAula);
		Alumno alumno = alumnoRepo.getById(idAlumno);
		
		aula.getAlumnos().add(alumno);
		aulaRepo.save(aula);
		
		return false;
		
		
		
	}

}

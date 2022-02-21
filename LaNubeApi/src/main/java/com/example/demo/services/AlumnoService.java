package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumno;
import com.example.demo.model.Tutor;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.TutorRepo;

@Service
public class AlumnoService {

	@Autowired
	public AlumnoRepo alumnoRepo;
	
	@Autowired
	public TutorRepo tutorRepo;
	
	
	public boolean addTutor(int idAlumno, int idTutor){
	
		Alumno alumno = alumnoRepo.getById(idAlumno);
		Tutor tutor = tutorRepo.getById(idTutor);
		
		
		alumno.getTutores().add(tutor);
		alumnoRepo.save(alumno);
		
		return false;
	}
}

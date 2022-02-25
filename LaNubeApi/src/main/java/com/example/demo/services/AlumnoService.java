package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlumnoIncompletoException;
import com.example.demo.exception.AulaNotFoundException;
import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Centro;
import com.example.demo.model.Tutor;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.CentroRepo;
import com.example.demo.repository.TutorRepo;

@Service
public class AlumnoService {

	@Autowired
	public AlumnoRepo alumnoRepo;
	
	@Autowired
	public TutorRepo tutorRepo;
	
	@Autowired
	public AulaRepo aulaRepo;
	
	@Autowired
	public CentroRepo centroRepo;
	
	
	public boolean addTutor(int idAlumno, int idTutor){
	
		Alumno alumno = alumnoRepo.getById(idAlumno);
		Tutor tutor = tutorRepo.getById(idTutor);
		
		
		alumno.getTutores().add(tutor);
		alumnoRepo.save(alumno);
		
		return false;
	}


	public void cambiarAula(int idCentro, int idAula, List findByAula) {

			Centro centro = centroRepo.getById(idCentro);
			int posicionCentro = centro.getAulas().indexOf(new Aula(idAula));
			Aula aula = centro.getAulas().get(posicionCentro);
		
			
			
			for (Object id2 : findByAula) {
			int id = (Integer) id2;

			Alumno alumno = alumnoRepo.getById(id);

			int posicion = aula.getAlumnos().indexOf(alumno);
			alumno.setAula(new Aula(1));
			
			if(aula.getAlumnos().contains(alumno)) {
				
			aula.getAlumnos().remove(aula.getAlumnos().indexOf(alumno));
				
			}
			alumnoRepo.save(alumno);
			
		}
		
		
		
	}
	
	
	
	
	public Alumno crearAlumno(Alumno alumno, int idCentro) throws Exception{
		
		
		
		
		if(alumno.getApellidos()==null || alumno.getNombre()==null || alumno.getNombre()=="" || alumno.getApellidos()=="" ){
			
			throw new AlumnoIncompletoException();
		
		} else if(alumno.getAula()==null || aulaRepo.existsById(alumno.getAula().getId())) {
			alumno.setAula(aulaRepo.getById(1));
		} 
			
		
		
		Centro centro = centroRepo.getById(idCentro);
		centro.getAlumnos().add(alumno);
		alumnoRepo.save(alumno);
		centroRepo.save(centro);
		
		
		return alumno;
	}
	
}

package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlumnoNotFoundException;
import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Centro;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.CentroRepo;


@Service
public class AulaService {

	@Autowired
	private AulaRepo aulaRepo;
	@Autowired
	private AlumnoRepo alumnoRepo;
	
	@Autowired
	private CentroRepo centroRepo;
	
	
	public boolean addAlumno(int idAula, int idAlumno) throws Exception{

		if(!alumnoRepo.existsById(idAlumno)) {
			throw new AlumnoNotFoundException(idAlumno+"");
		}
		
		Aula aula = aulaRepo.getById(idAula);
		Alumno alumno = alumnoRepo.getById(idAlumno);
		
		alumno.setAula(aula);
		alumnoRepo.save(alumno);
		
		aula.getAlumnos().add(alumno);
		aulaRepo.save(aula);
		return false;
		
		
		
	}
	
	
	public Aula actualizaAula(int idCentro, int idAula, Aula aula) {
		
		Centro centro = centroRepo.getById(idCentro);
		Aula aulaContenida = new Aula(idAula);
		int posicion=centro.getAulas().indexOf(aulaContenida);
		
		Aula aulaModificada = centro.getAulas().get(posicion);
		
		if(aula.getNombre()!=null && aula.getNombre()!="") {
			
			aulaModificada.setNombre(aula.getNombre());
			aulaRepo.save(aulaModificada);
		}
		
		return aulaModificada;
		
	}

}

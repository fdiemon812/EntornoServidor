package com.example.demo.services;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlumnoNotFoundException;
import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Centro;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.CentroRepo;

/**
 * Servicio para trabajar con aulas
 * @author estudiante
 *
 */
@Service
public class AulaService {

	@Autowired
	private AulaRepo aulaRepo;
	@Autowired
	private AlumnoRepo alumnoRepo;
	
	@Autowired
	private CentroRepo centroRepo;
	
	/**
	 * Añade un alumno a un aula
	 * @param idAula
	 * @param idAlumno
	 * @return
	 * @throws Exception
	 */
	public Alumno addAlumno(int idAula, int idAlumno) throws Exception{

		if(!alumnoRepo.existsById(idAlumno)) {
			throw new AlumnoNotFoundException(idAlumno+"");
		}
		
		
		
		Aula aula = aulaRepo.getById(idAula);
		Alumno alumno = alumnoRepo.getById(idAlumno);
		
		
		Aula aulaOld = aulaRepo.getById(alumnoRepo.getById(idAlumno).getAula().getId());
	
		int i=0;
		int posicionAula=-1;
		
	    Iterator<Alumno> alumn = aulaOld.getAlumnos().iterator();
	    System.out.println(aula.getAlumnos().size());
		while (alumn.hasNext() && posicionAula==-1) {	
			Alumno alum =  alumn.next();
			if(alum.getId() ==idAlumno) {
				posicionAula=i;
			}
			i++;
		}
		if(posicionAula!=-1) {
			
			aulaOld.getAlumnos().remove(posicionAula);
			
			
			
			alumno.setAula(aula);
			alumnoRepo.save(alumno);
			
			
			aulaRepo.save(aulaOld);
			aula.getAlumnos().add(alumno);
			aulaRepo.save(aula);
		}

		

		return alumno;
		
		
		
	}
	
	/**
	 * Actualiza los datos de un aula
	 * @param idCentro
	 * @param idAula
	 * @param aula
	 * @return
	 */
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

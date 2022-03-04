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
		
		System.out.println("Borrando aula");
		
		Aula aulaOld = aulaRepo.getById(alumnoRepo.getById(idAlumno).getAula().getId());
		System.out.println(aulaOld.getNombre());
		
		Alumno alumnoOld= new Alumno(idAlumno);
		
		int posicionAula = aula.getAlumnos().indexOf(alumnoOld);
		
		System.out.println(posicionAula);
		
		aulaOld.getAlumnos().remove(posicionAula);
	
		System.out.println("guardando alumno");

		
		alumno.setAula(aula);
		alumnoRepo.save(alumno);
		
		
		aulaRepo.save(aulaOld);
		aula.getAlumnos().add(alumno);
		aulaRepo.save(aula);

		

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

package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AlumnoIncompletoException;
import com.example.demo.exception.AulaNotFoundException;
import com.example.demo.exception.ComidaInvalidException;
import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Centro;
import com.example.demo.model.Tutor;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.CentroRepo;
import com.example.demo.repository.TutorRepo;


/**
 * Metodos para trabajar con alumno
 * @author estudiante
 *
 */
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
	
	
	
	/**
	 * Añade tutores a los alumnos
	 * @param idAlumno
	 * @param idTutor
	 * @return
	 */
	public boolean addTutor(int idAlumno, int idTutor){
	
		Alumno alumno = alumnoRepo.getById(idAlumno);
		Tutor tutor = tutorRepo.getById(idTutor);
		
		
		alumno.getTutores().add(tutor);
		alumnoRepo.save(alumno);
		
		return false;
	}

/**
 * Recibe un id aula, IdCentro y una lista de alumnos. Cambia a todos los alumnos de aula a "SinAula"
 * @param idCentro
 * @param idAula
 * @param findByAula
 */
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
	
	
	
	/**
	 * Recibe un alumno y el id de un centro. Crea un alumno. 
	 * @param alumno
	 * @param idCentro
	 * @return
	 * @throws Exception
	 */
	public Alumno crearAlumno(Alumno alumno, int idCentro) throws Exception{
		
		
		
		
		if(alumno.getApellidos()==null || alumno.getNombre()==null || alumno.getNombre()=="" || alumno.getApellidos()=="" ){
			
			throw new AlumnoIncompletoException();
		
		} else if(alumno.getAula()==null || !aulaRepo.existsById(alumno.getAula().getId())) {
			alumno.setAula(aulaRepo.getById(1));
			
		}else if(alumno.getComida()==null || !( alumno.getComida().equals("Comida" ) || alumno.getComida().equals("Biberón" ) || alumno.getComida().equals("Biberón y Comida" ))){
			throw new ComidaInvalidException();
		} else {
			
			alumno.setAula(aulaRepo.getById(alumno.getAula().getId()));
		}
			
		
		
		Centro centro = centroRepo.getById(idCentro);
		centro.getAlumnos().add(alumno);
		Aula aulaPasada = aulaRepo.getById(alumno.getAula().getId());
		aulaPasada.getAlumnos().add(alumno);
		
		alumnoRepo.save(alumno);
		aulaRepo.save(aulaPasada);
		centroRepo.save(centro);
		
		
		return alumno;
	}
	
	/**
	 * Borra un alumno dentro de un centro
	 * @param centro
	 * @param idAlumno
	 */
	public void borrarAlumno(Centro centro, int idAlumno) {
		
		Alumno alumno = new Alumno(idAlumno);
		int posicion=centro.getAlumnos().indexOf(alumno);
		centro.getAlumnos().remove(posicion);
		Aula aula = aulaRepo.getById(alumnoRepo.getById(idAlumno).getAula().getId());
		int posicionAula = aula.getAlumnos().indexOf(alumno);
		aula.getAlumnos().remove(posicionAula);
		
		
		
		
		aulaRepo.save(aula);
		centroRepo.save(centro);
		alumnoRepo.deleteById(idAlumno);
		
		
		
	}

	/**
	 * Actualiza los datos de un alumno
	 * @param centro
	 * @param idAlumno
	 * @param alumno
	 * @return
	 */
	public Alumno actualizaAlumno(Centro centro, int idAlumno, Alumno alumno) {

		Alumno alumnoAntiguo = alumnoRepo.getById(idAlumno);
		
		alumnoAntiguo.setApellidos(alumno.getApellidos());
		alumnoAntiguo.setDireccion(alumno.getDireccion());
		alumnoAntiguo.setDni(alumno.getDni());
		alumnoAntiguo.setFechaNacimiento(alumno.getFechaNacimiento());
		alumnoAntiguo.setObservaciones(alumno.getObservaciones());
		
		alumnoAntiguo.setComida(alumno.getComida());
		alumnoAntiguo.setNombre(alumno.getNombre());
		alumnoAntiguo.setHoraEntrada(alumno.getHoraEntrada());
		alumnoAntiguo.setHoraSalida(alumno.getHoraSalida());
		alumnoAntiguo.setComeEnCentro(alumno.isComeEnCentro());
		alumnoAntiguo.setAlta(alumno.isAlta());
		alumnoRepo.save(alumnoAntiguo);
		
		
		return alumnoAntiguo;
	}
	
}

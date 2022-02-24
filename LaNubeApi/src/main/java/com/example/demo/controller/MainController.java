package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.AulaNotFoundException;
import com.example.demo.exception.CentroNotFoundException;
import com.example.demo.exception.AlumnoNotFoundException;
import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Centro;
import com.example.demo.model.Tutor;
import com.example.demo.model.Usuario;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.CentroRepo;
import com.example.demo.repository.TutorRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.AulaService;
import com.example.demo.services.CentroService;
import com.fasterxml.jackson.databind.JsonMappingException;



/**
 * Controlador principal de la aplicación
 * @author estudiante
 *
 */
@RestController
public class MainController {

	
	@Autowired
	private AlumnoRepo alumnoRepo;
	
	@Autowired
	private TutorRepo tutorRepo;
	
	@Autowired
	private CentroRepo centroRepo;
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private AulaService aulaService;
	
	@Autowired
	private AulaRepo aulaRepo;
	
	@Autowired
	private UserRepo usuarioRepo;
	
	@Autowired
	private CentroService centroService;
	
	
	/**
	 * Crea un centro 
	 */
	@PostMapping("/centro")
	public Centro creaCentro(@RequestBody Centro centro) {
		
		centroRepo.save(centro);
		
		return centro;
	}
	
	/**
	 * Crea un centro 
	 */
	@GetMapping("/centro/{id}")
	public Centro creaCentro(@PathVariable int id) {
		
		
		
		
		return centroRepo.getById(id);
	}
	
	/**
	 * Modifica un centro y añade un aula si no la tenia. 
	 * @param centroNuevo
	 * @param id
	 * @return
	 */
	@PutMapping("/centro/{id}")
	public Centro modificaCentro(@RequestBody Centro centroNuevo, @PathVariable int id) throws Exception{
		
		if(!centroRepo.existsById(id)) {
			
			throw new CentroNotFoundException(id+"");
		}
		
		Centro centroModificado = centroRepo.getById(id);
		
		if(!aulaRepo.existsById(centroNuevo.getAulas().get(0).getId())) {

			throw new AulaNotFoundException(centroNuevo.getAulas().get(0).getId()+"");
		}
		centroService.modificarCentro(centroModificado, centroNuevo);
		centroRepo.save(centroModificado);
		
		
		return centroModificado;
		
	}
	
	/**
	 * Borra un centro recibiendo un ID
	 * @param id
	 */
	@DeleteMapping("centro/{id}")
	public void borradoCentro(@PathVariable int id) throws Exception{
		
		
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}
		Centro centro = centroRepo.getById(id);
		
		centroRepo.delete(centro);
		
		
	}
	
	
	/**
	 * Devuelve una lista de todas las aulas disponibles. 
	 * @return
	 */
	@GetMapping("/aulas")
	public List<Aula> obtenerAulas(){
		return aulaRepo.findAll();
	}
	
	
	/**
	 * Agrega un aula a un alumno
	 * @param alumno
	 * @return
	 */
	@PutMapping("/aula/{idAula}")
	public ResponseEntity<List<Alumno>>  registrarAulaAlumno(@RequestBody Alumno alumno, @PathVariable int idAula ) throws Exception{
		System.out.println("hola aula");
		Aula aula = aulaRepo.getById(idAula);
				
		ResponseEntity respuesta = ResponseEntity.ok(aula.getAlumnos());
		
		aulaService.addAlumno(idAula, alumno.getId());
		
		
		
		return respuesta;
	}
	
	
	
	
		
	
	
	
	
	
	/**
	 * Devuelve una lista completa de alumnos en el centro. 
	 * @return
	 */
	@GetMapping("/alumno")
	public List<Alumno> listarAlumnos(){
		
		return alumnoRepo.findAll();
	}
	
	/**
	 * Registra alumnos 
	 * @param alumno
	 * @return
	 */
	@PostMapping("/alumno")
	public Alumno  registrarAlumno(@RequestBody Alumno alumno )  throws Exception{

		alumnoRepo.save(alumno);
		
		return alumno;
	}
	
	
	
	
	
	
	
	/**
	 * Agrega un tutor a un alumno
	 * @param alumno
	 * @return
	 */
	@PutMapping("/alumno/{idAlumno}")
	public ResponseEntity<List<Alumno>>  registrarTutorAlumno(@RequestBody Tutor tutor, @PathVariable int idAlumno ) throws Exception{
		
		
	
		
		Tutor tutor2=tutorRepo.findByEmail(tutor.getEmail());
	
		if(tutor2==null) {
			throw new AlumnoNotFoundException(idAlumno+"");
		}

		Alumno alumno = alumnoRepo.getById(idAlumno);
				
		
		alumnoService.addTutor(idAlumno, tutor2.getId());
		ResponseEntity respuesta = ResponseEntity.ok(alumno.getTutores());
		
		
		
		return respuesta;
	}
	
	
	
	
	/**
	 * Agrega un tutor a un alumno
	 * @param alumno
	 * @return
	 */
	@DeleteMapping("/alumno/{idAlumno}")
	public ResponseEntity<List<Alumno>>  BorrarAlumno(@RequestBody Tutor tutor, @PathVariable int idAlumno ) throws Exception{
		
		
	
		
		Tutor tutor2=tutorRepo.findByEmail(tutor.getEmail());
	
		if(tutor2==null) {
			throw new AlumnoNotFoundException(idAlumno+"");
		}

		Alumno alumno = alumnoRepo.getById(idAlumno);
				
		
		alumnoService.addTutor(idAlumno, tutor2.getId());
		ResponseEntity respuesta = ResponseEntity.ok(alumno.getTutores());
		
		
		
		return respuesta;
	}
	
	
	
	
	
	
	
	
	@GetMapping("/usuario")
	public boolean isUsuario(String email){
		boolean respuesta = false;
		Usuario usuario=usuarioRepo.findByEmail(email).orElse(null);

		
		if(usuario==null) {
			respuesta=true;
		}
		
		
		return respuesta;
		
	}
	
	
	/**
	 * Para cuando existe un error de un JSON mal formado
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException jsonException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), jsonException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	/**
	 * Gestiona si no existe un usuario buscado
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(AlumnoNotFoundException.class)
	public ResponseEntity<ApiError> AlumnoException(AlumnoNotFoundException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	
	/**
	 * Gestiona si no existe un usuario buscado
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(AulaNotFoundException.class)
	public ResponseEntity<ApiError> AulaException(AulaNotFoundException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/**
	 * Gestiona si no existe un usuario buscado
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(CentroNotFoundException.class)
	public ResponseEntity<ApiError> CentroException(CentroNotFoundException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	
	
	
}
	



package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Tutor;
import com.example.demo.model.Usuario;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.TutorRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.AlumnoService;
import com.example.demo.services.AulaService;
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
	private AlumnoService alumnoService;
	
	@Autowired
	private AulaService aulaService;
	
	@Autowired
	private AulaRepo aulaRepo;
	
	@Autowired
	private UserRepo usuarioRepo;
	
	
	
		
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
	public Alumno  registrarAlumno(@RequestBody Alumno alumno ) {

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
		
		
		System.out.println(tutor.getEmail());
		System.out.println(tutor.getDni());
		System.out.println(tutor.getNombre());
		
		Tutor tutor2=tutorRepo.findByEmail(tutor.getEmail());
		System.out.println(tutor2.getEmail());
		System.out.println(tutor2.getId());

		Alumno alumno = alumnoRepo.getById(idAlumno);
				
		
		alumnoService.addTutor(idAlumno, tutor2.getId());
		ResponseEntity respuesta = ResponseEntity.ok(alumno.getTutores());
		
		
		
		return respuesta;
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
	 * Devuelve una lista de todas las aulas disponibles. 
	 * @return
	 */
	@GetMapping("/aulas")
	public List<Aula> obtenerAulas(){
		return aulaRepo.findAll();
	}
	
	
	
	@GetMapping("/usuario")
	public ResponseEntity<Usuario> obtenerUsuario(String email){
		
		ResponseEntity<Usuario> respuesta = ResponseEntity.notFound().build();
		Usuario usuario=usuarioRepo.findByEmail(email).orElse(null);
		if(usuario!=null) {
			
		respuesta = ResponseEntity.ok(usuario);
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
	
	
	
	
	
	
}
	



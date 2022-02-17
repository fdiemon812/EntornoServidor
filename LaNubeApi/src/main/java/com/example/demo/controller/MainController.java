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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ApiError;
import com.example.demo.model.Alumno;
import com.example.demo.repository.AlumnoRepo;
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
	
		
	/**
	 * Devuelve una lista completa de alumnos en el centro. 
	 * @return
	 */
	@GetMapping("/alumno")
	public List<Alumno> listarAlumnos(){
		
		return alumnoRepo.findAll();
	}
	
	/**
	 * 
	 * @param alumno
	 * @return
	 */
	@PostMapping("/alumno")
	public Alumno  registrarAlumno(@RequestBody Alumno alumno ) {
		System.out.println("Entrando en registro alumno");
//		System.out.println(alumno.getAula().getNombre());
//		System.out.println(alumno.getAula().getId());

		System.out.println(alumno.getApellidos());
		alumnoRepo.save(alumno);
		
		return alumno;
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
	
//	@GetMapping("/alumno")
//	public Alumno getAlumno(){
//		System.out.println(alumnoRepo.getById(1));
//		return alumnoRepo.getById(1);
//	}
//	
//	
	
//	@GetMapping("/alumno/{id}")
//	public Alumno getAlumno(@PathVariable Integer idAlumno){
//		System.out.println(alumnoRepo.getById(idAlumno));
//		return alumnoRepo.getById(idAlumno);
//	}


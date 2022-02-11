package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Alumno;
import com.example.demo.repository.AlumnoRepo;



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
	 * Si el token es valido no devuelve nada. Si el token no es válido devuelve error. 
	 */
	@GetMapping("/home/token")
    public void compruebaToken() {}
	
	
	
	
	@GetMapping("/alumnos")
	public List<Alumno> listarAlumnos(){
		
		return alumnoRepo.findAll();
	}
	
	
//	@GetMapping("/alumno/{id}")
//	public Alumno getAlumno(@PathVariable Integer idAlumno){
//		System.out.println(alumnoRepo.getById(idAlumno));
//		return alumnoRepo.getById(idAlumno);
//	}
	
	@GetMapping("/alumno")
	public Alumno getAlumno(){
		System.out.println(alumnoRepo.getById(1));
		return alumnoRepo.getById(1);
	}
}

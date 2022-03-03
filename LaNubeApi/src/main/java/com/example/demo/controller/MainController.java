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
import com.example.demo.exception.AulaCentroNotFoundException;
import com.example.demo.exception.AulaNotFoundException;
import com.example.demo.exception.CentroNotFoundException;
import com.example.demo.exception.ComidaInvalidException;
import com.example.demo.exception.AlumnoCentroNotFoundException;
import com.example.demo.exception.AlumnoIncompletoException;
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
import com.fasterxml.jackson.core.JsonParseException;
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
		
		Aula aula = new Aula("Sin aula");
		centro.getAulas().add(aula);
		aulaRepo.save(aula);
		centroRepo.save(centro);
		
		return centro;
	}
	
	/**
	 * Devuelve un centro 
	 */
	@GetMapping("/centro/{id}")
	public Centro creaCentro(@PathVariable int id) {
		
		
		
		
		return centroRepo.getById(id);
	}
	
	
	/**
	 * Devuelve los centros 
	 */
	@GetMapping("/centros")
	public List<Centro> creaCentro() {
		
		
		
		
		return  centroRepo.findAll();
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
	@GetMapping("centro/{id}/aulas")
	public List<Aula> obtenerAulas(@PathVariable int id) throws Exception{
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}
		Centro centro =centroRepo.getById(id);
		
		return centro.getAulas();
	}
	
	
	/**
	 * Devuelve una lista de todas las aulas disponibles. 
	 * @return
	 */
	@GetMapping("centro/{id}/aula/{idAula}")
	public Aula obtenerAula(@PathVariable int id, @PathVariable int idAula) throws Exception{
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}
		Centro centro =centroRepo.getById(id);
		Aula aulaContenida = new Aula(idAula);
		int posicion=centro.getAulas().indexOf(aulaContenida);
		
		
		if(posicion==-1) {
			throw new AulaCentroNotFoundException(idAula+"");
		}
		
		return aulaRepo.getById(idAula);
	}
	
	
	/**
	 * Devuelve una lista de los alumn os de un aula. 
	 * @return
	 */
	@GetMapping("centro/{id}/aula/{idAula}/alumnos")
	public List<Alumno> obtenerAlumnosAula(@PathVariable int id, @PathVariable int idAula) throws Exception{
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}
		Centro centro =centroRepo.getById(id);
		Aula aulaContenida = new Aula(idAula);
		int posicion=centro.getAulas().indexOf(aulaContenida);
		
		
		if(posicion==-1) {
			throw new AulaCentroNotFoundException(idAula+"");
		}
		
		
		Aula aula1 = aulaRepo.getById(idAula);
		
		return aula1.getAlumnos();
		}
	
	
	/**
	 * Crea un aula en un centro determinado
	 * @param id
	 * @param aula
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/centro/{id}/aula")
	public Aula crearAula(@PathVariable int id, @RequestBody Aula aula) throws Exception{
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}
		
		Centro centro = centroRepo.getById(id);
		Aula aula2 = new Aula(aula.getNombre());
		centro.getAulas().add(aula2);
		aulaRepo.save(aula2);
		centroRepo.save(centro);
		
		
		return aula2;
		
		
	}
	
	/**
	 * Borra un aula por su id de un centro por su id
	 * @param id
	 * @param idAula
	 * @throws Exception
	 */
	@DeleteMapping("/centro/{id}/aula/{idAula}")
	public void borrarAula(@PathVariable int id, @PathVariable int idAula) throws Exception {
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}else if(!aulaRepo.existsById(idAula)) {
			throw new AulaNotFoundException(idAula+"");
		}
		Centro centro = centroRepo.getById(id);
		Aula aulaContenida = new Aula(idAula);
		int posicion=centro.getAulas().indexOf(aulaContenida);
		if(posicion==-1){
			throw new AulaCentroNotFoundException(idAula+"");

		}
		
		alumnoService.cambiarAula(id, idAula, alumnoRepo.findByAula(idAula));
		centroService.borrarAula(id, idAula);
		aulaRepo.deleteById(idAula);
	}
	
	
	@PutMapping("centro/{id}/aula/{idAula}")
	public Aula modificarAula(@PathVariable int id, @PathVariable int idAula, @RequestBody Aula aulaNueva, Integer idAlumno) throws Exception{
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}else if(!aulaRepo.existsById(idAula)) {
			throw new AulaNotFoundException(idAula+"");
		}
		
		Centro centro = centroRepo.getById(id);
		Aula aulaContenida = new Aula(idAula);
		int posicion=centro.getAulas().indexOf(aulaContenida);
		
		if(posicion==-1){
			throw new AulaCentroNotFoundException(idAula+"");
			}
	
		
		Aula aulaModificada = aulaService.actualizaAula(id, idAula, aulaNueva);
		
		

		if(idAlumno!=null) {
			
			aulaService.addAlumno(idAula, idAlumno);
}
		
		
		return aulaModificada;
		
	}
	
	
	
	
	
	
	//ALUMNOS
	
	/**
	 * Registra alumnos 
	 * @param alumno
	 * @return
	 */
	@PostMapping("/centro/{id}/alumno")
	public Alumno  matricularAlumno(@RequestBody Alumno alumno , @PathVariable int id)  throws Exception{

			
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}
			
		else if(!aulaRepo.existsById(alumno.getAula().getId())) {
			throw new AulaNotFoundException(alumno.getAula().getId()+"");
		}
		
		Centro centro = centroRepo.getById(id);
		Aula aulaContenida = new Aula(alumno.getAula().getId());
		int posicion=centro.getAulas().indexOf(aulaContenida);
		
		if(posicion==-1){
			throw new AulaCentroNotFoundException(alumno.getAula().getId()+"");
			}
		

		return alumnoService.crearAlumno(alumno, id);
	}
	
	/**
	 * Devuelve todos los alumnos de un centro
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("centro/{id}/alumnos")
	public List<Alumno> listarAlumnosCentro(@PathVariable int id) throws Exception {
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		}
		
		
		return centroRepo.getById(id).getAlumnos();
		
	}
	
	
	/**
	 * Devuelve un alumno de un centro concreto
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("centro/{id}/alumno/{idAlumno}")
	public Alumno obtenerAlumnoDeCentro(@PathVariable int id, @PathVariable int idAlumno) throws Exception {
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		} 
			
		Centro centro = centroRepo.getById(id);
		Alumno alumno = new Alumno(idAlumno);
		int posicion=centro.getAlumnos().indexOf(alumno);
		if(posicion==-1){
			throw new AlumnoCentroNotFoundException(idAlumno);
		}
		
		
		return alumnoRepo.getById(idAlumno);
		
	}
	
	
	@PutMapping("centro/{id}/alumno/{idAlumno}")
	public Alumno editarAlumno(@RequestBody Alumno alumno2, @PathVariable int id, @PathVariable int idAlumno) throws Exception{
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		} 
		
		Centro centro = centroRepo.getById(id);
		Alumno alumno = new Alumno(idAlumno);
		int posicion=centro.getAlumnos().indexOf(alumno);
		if(posicion==-1){
			throw new AlumnoCentroNotFoundException(idAlumno);
		}
		
		
		return 		alumnoService.actualizaAlumno(centro, idAlumno, alumno);

	}
	
	
	
	/**
	 * Borra un alumno de un centro concreto
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("centro/{id}/alumno/{idAlumno}")
	public void borrarAlumnoDeCentro(@PathVariable int id, @PathVariable int idAlumno) throws Exception {
		
		if(!centroRepo.existsById(id)) {
			throw new CentroNotFoundException(id+"");
		} 
			
		Centro centro = centroRepo.getById(id);
		Alumno alumno = new Alumno(idAlumno);
		int posicion=centro.getAlumnos().indexOf(alumno);
		if(posicion==-1){
			throw new AlumnoCentroNotFoundException(idAlumno);
		}
		
		
		alumnoService.borrarAlumno(centro, idAlumno);
		

		
		
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
	 * Borra un tutor a un alumno
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
	 * Para cuando existe un error de un JSON mal formado
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<ApiError> handleJsonParsegException(JsonParseException jsonException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), "JSON MAL FORMADO");
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
	
	
	/**
	 * Gestiona si un aula buscada no existe en el centro
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(AulaCentroNotFoundException.class)
	public ResponseEntity<ApiError> AulaCentroException(AulaCentroNotFoundException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		
		

	}
	
	/**
	 * Gestiona si un alumno es creado con falta de información
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(AlumnoIncompletoException.class)
	public ResponseEntity<ApiError> AlumnoIncompletoException(AlumnoIncompletoException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	
	
	/**
	 * Gestiona si un alumno es creado con falta de información
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(AlumnoCentroNotFoundException.class)
	public ResponseEntity<ApiError> AlumnoCentroNotFoundException(AlumnoCentroNotFoundException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	
	/**
	 * Gestiona si se intenta crear un alumno con comida invalida
	 * @param ex
	 * @return JSON bien formado
	 */
	@ExceptionHandler(ComidaInvalidException.class)
	public ResponseEntity<ApiError> ComidaInvalidException(ComidaInvalidException userException) {
		ApiError apiError = new ApiError(LocalDateTime.now(), userException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	
	
	
}
	



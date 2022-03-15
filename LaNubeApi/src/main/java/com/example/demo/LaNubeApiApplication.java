package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Centro;
import com.example.demo.model.Profesor;
import com.example.demo.model.Tutor;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.CentroRepo;
import com.example.demo.repository.UserRepo;

@SpringBootApplication
public class LaNubeApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(LaNubeApiApplication.class, args);
	}

//
//	@Bean
//	CommandLineRunner iniData (UserRepo usuRepo, PasswordEncoder pass) {
//		return (args) -> {
//			usuRepo.saveAll(Arrays.asList(new Profesor("Admin","Admin","00000000X", "admin@admin.com","666666666",pass.encode("admin"), "ADMINISTRADOR"),
//					new Profesor("Admin","Admin","00000000X", "tutor@admin.com","666666666",pass.encode("tutor"), "TUTOR"),
//					new Profesor("Admin","Admin","00000000X", "profesor@admin.com","666666666",pass.encode("profesor"), "PROFESOR")));
//		};
//	}
//
//	
//	@Bean
//	CommandLineRunner iniData2 (AlumnoRepo alumnoRepo, AulaRepo aulaRepo, CentroRepo centroRepo) {
//		ArrayList<Aula> aulas = new ArrayList<>();
//		aulaRepo.save( new Aula("Sin aula"));
//		aulaRepo.save( new Aula("Margarita"));
//		aulaRepo.save( new Aula("Romero"));
//		aulaRepo.save( new Aula("Tulipan"));
//		
//		aulas.add(aulaRepo.getById(1));
//		aulas.add(aulaRepo.getById(2));
//		aulas.add(aulaRepo.getById(3));
//		centroRepo.save( new Centro("nube","Urquiza 9" , "954414986", "centro@centro.com",  aulas));
//		
//		List<Alumno> alumnos = Arrays.asList(
//				new Alumno("nombre2", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00" , "00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre3", "apellidos", "dni2", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre4", "apellidos", "dni3", "direccion5", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre5", "apellidos", "dni8", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre6", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre7", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nomffbre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nomdbre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nombre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nomdbre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(1)),
//				new Alumno("nomdbre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(2)),
//				new Alumno("nombdfre", "apellidos", "dni", "direccion", LocalDate.now(),
//						"00:00","00:00" ,"comida",false,
//						"observaciones", aulaRepo.getById(2)));
//		
//		
//		
//		
//		
//		
//		return (args) -> {
//			alumnoRepo.saveAll(Arrays.asList(
//					new Alumno("nombre2", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00" , "00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre3", "apellidos", "dni2", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre4", "apellidos", "dni3", "direccion5", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre5", "apellidos", "dni8", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre6", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre7", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nomffbre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nomdbre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nombre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nomdbre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(1)),
//					new Alumno("nomdbre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(2)),
//					new Alumno("nombdfre", "apellidos", "dni", "direccion", LocalDate.now(),
//							"00:00","00:00" ,"comida",false,
//							"observaciones", aulaRepo.getById(2))
//					));
//		};
//	}
	
	
//	@Bean
//	CommandLineRunner iniData3 (AulaRepo aulaRepo) {
//		return (args) -> {
//			
//			aulaRepo.save( new Aula("Margarita"));
//			aulaRepo.save( new Aula("Romero"));
//			aulaRepo.save( new Aula("Tulipan"));
//			
//			
//		};
//	}

}

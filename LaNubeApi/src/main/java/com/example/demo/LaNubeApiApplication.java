package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Alumno;
import com.example.demo.model.Aula;
import com.example.demo.model.Profesor;
import com.example.demo.model.Tutor;
import com.example.demo.repository.AlumnoRepo;
import com.example.demo.repository.AulaRepo;
import com.example.demo.repository.UserRepo;

@SpringBootApplication
public class LaNubeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaNubeApiApplication.class, args);
	}


	@Bean
	CommandLineRunner iniData (UserRepo usuRepo, PasswordEncoder pass) {
		return (args) -> {
			usuRepo.saveAll(Arrays.asList(new Profesor("Admin","Admin","00000000X", "admin@admin.com","666666666",pass.encode("admin"), "ADMINISTRADOR")));
		};
	}

	
	@Bean
	CommandLineRunner iniData2 (AlumnoRepo alumnoRepo) {
		return (args) -> {
			alumnoRepo.saveAll(Arrays.asList(
					new Alumno("nombre1", "apellidos1", "dni1"),
					new Alumno("aaa", "aa", "aa"),
					new Alumno("bbb", "bbbb", "bb"),
					new Alumno("cccc", "ccccc", "ccccc")));
		};
	}
	
	
	@Bean
	CommandLineRunner iniData3 (AulaRepo aulaRepo) {
		return (args) -> {
			
			aulaRepo.save( new Aula("margarita"));
			aulaRepo.save( new Aula("romero"));
			aulaRepo.save( new Aula("tulipan"));
			
			
		};
	}

}

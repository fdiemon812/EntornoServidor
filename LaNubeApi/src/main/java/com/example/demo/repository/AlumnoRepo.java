package com.example.demo.repository;

import java.util.Optional;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Alumno;

/**
 * Clase para comunicar 
 * @author estudiante
 *
 */
public interface AlumnoRepo extends JpaRepository<Alumno, Integer> {

	/**
	 * Consulta SQL para obtener alumnos por aula
	 * @param id
	 * @return
	 */
	  @Query ("select id from Alumno where aula_id= :id")
	  List findByAula(@Param("id") int id);
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Tutor;

/**
 * Conecta Tutor con la BBDD
 * @author estudiante
 *
 */
public interface TutorRepo extends JpaRepository<Tutor, Integer> {

	public Tutor findByEmail(String email);
	
	
	/**
	 * Consulta SQL para obtener tutores por centro @Query ("select id from Alumno where centro_id= :id")
	 * @param id
	 * @return
	 */
	  @Query (value="select * from usuario, centro_tutores "
	  		+ "where usuario.id=centro_tutores.tutores_id "
	  		+ "and centro_tutores.centro_id=:id order by apellidos", nativeQuery=true)
	  List<Tutor> findAllTutoresByCentro(@Param("id") int id);
	  
	  
	  
	  /**
		 * Consulta SQL para obtener tutores por alumno @Query ("select id from Alumno where centro_id= :id")
		 * @param id
		 * @return
		 */
		  @Query (value="Select * from usuario, alumno_tutores"
		  		+ " where usuario.id=alumno_tutores.tutores_id and alumno_id=:id"
				  , nativeQuery=true)
		  List<Tutor> findAllTutoresByAlumno(@Param("id") int id);
}
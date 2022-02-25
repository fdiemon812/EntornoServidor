package com.example.demo.repository;

import java.util.Optional;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Alumno;


public interface AlumnoRepo extends JpaRepository<Alumno, Integer> {

	
	  @Query ("select id from Alumno where aula_id= :id")
	  List findByAula(@Param("id") int id);
}

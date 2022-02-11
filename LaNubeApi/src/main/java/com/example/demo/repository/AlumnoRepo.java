package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Alumno;
import com.example.demo.model.Usuario;

public interface AlumnoRepo extends JpaRepository<Alumno, Long> {

}

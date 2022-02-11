package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Alumno;

public interface AlumnoRepo extends JpaRepository<Alumno, Integer> {

}

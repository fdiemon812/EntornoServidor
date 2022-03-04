package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Aula;

/**
 * Conecta Aula con la base de datos
 * @author estudiante
 *
 */
public interface AulaRepo extends JpaRepository<Aula, Integer> {

}

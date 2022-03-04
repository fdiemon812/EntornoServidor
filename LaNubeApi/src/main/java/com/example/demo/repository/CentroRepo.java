package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Centro;

/**
 * Conecta centro con BBDD
 * @author estudiante
 *
 */
public interface CentroRepo extends JpaRepository<Centro, Integer> {

}

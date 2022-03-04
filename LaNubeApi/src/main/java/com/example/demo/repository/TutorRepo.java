package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tutor;

/**
 * Conecta Tutor con la BBDD
 * @author estudiante
 *
 */
public interface TutorRepo extends JpaRepository<Tutor, Integer> {

	public Tutor findByEmail(String email);
}
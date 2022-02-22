package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tutor;

public interface TutorRepo extends JpaRepository<Tutor, Integer> {

	public Tutor findByEmail(String email);
}
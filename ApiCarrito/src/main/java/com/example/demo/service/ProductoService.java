package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {

	
	
	@Autowired
	private ProductoRepository productoRepo;

	public List<Producto> findAll() {
		return productoRepo.findAll();
	}

	public Producto findById(Integer id) {
		return productoRepo.getById(id);
	}
	
	
	public boolean contains(int id) {

		return productoRepo.existsById(id);
	}
}

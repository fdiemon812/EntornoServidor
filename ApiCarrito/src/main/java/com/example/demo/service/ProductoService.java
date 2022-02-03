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

	
	/**
	 * Devuelve todos los productos
	 * @return
	 */
	public List<Producto> findAll() {
		return productoRepo.findAll();
	}

	
	/**
	 * Devuelve un producto por ID
	 * @param id
	 * @return
	 */
	public Producto findById(Integer id) {
		return productoRepo.getById(id);
	}
	
	/**
	 * Devuielve true si existe esa ID
	 * @param id
	 * @return
	 */
	public boolean contains(int id) {

		return productoRepo.existsById(id);
	}
}

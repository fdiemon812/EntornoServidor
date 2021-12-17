package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;

public class EmpleadoServiceDB implements EmpleadoService{

	@Autowired
	private EmpleadoRepository repositorio;
	
	@Override
	public Empleado add(Empleado e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado edit(Empleado e) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;

@Service
public class EmpleadoService {
	
	
	
	private List<Empleado> repositorio = new ArrayList<Empleado>();
	
	
	
	
	public Empleado add(Empleado e) {
		
		repositorio.add(e);
		return e;
	}
	
	
	public List<Empleado> findAll(){
		
		
		
		return repositorio;
		
		
	}
	
	
	@PostConstruct
	public void init() {
		repositorio.addAll(Arrays.asList(new Empleado(1 , "Jose", "mail1", "66666666"),
				new Empleado(1 , "pepe", "mail2", "11111"),
				new Empleado(1 , "Cris", "mail3", "99999")));
	}

}

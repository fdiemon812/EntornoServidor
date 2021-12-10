package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class UsuarioService {
	
	
	private HashSet<Usuario> listaUsuarios = new HashSet<Usuario>();
	private ArrayList<Producto> listaProductosDefecto = new ArrayList<Producto>();
	
	
	
	public void addPedido(int posicion, Pedido pedido, int cantidad) {
		for(int i=0;i<cantidad;i++) {
			
			pedido.listaProductos.add(listaProductosDefecto.get(posicion));
		}
	}
	
	public boolean compruebaUsuario(String usuario, String password) {
		
		boolean result=false;
		
		Usuario user = new Usuario(usuario, password);
		
		if(listaUsuarios.contains(user)) {
			
			ArrayList<Usuario> listaUsuariosArray = new ArrayList<Usuario>(listaUsuarios);
			
			Usuario usuarioBD = listaUsuariosArray.get(listaUsuariosArray.indexOf(user));
			
			if(usuarioBD.getUser().equalsIgnoreCase(usuario)&& usuarioBD.getPassword().equals(password)) {
				result=true;
			}
			
			
			
			
		}
		
		
		return result;
		
	}
	
	@PostConstruct
	public void init() {
		listaUsuarios.addAll(
				Arrays.asList(new Usuario("user","José", "Pérez", "user", "C/Málaga, 9 7A", 650000000, "user@user.com"),
						new Usuario("admin","Antonia", "García", "admin", "C/Arroyo, 9 7A", 690000000, "admin@admin.com"),
						new Usuario("flavio","Flavio", "de Diego", "flavio", "C/Jaen, 9 7A", 850000000, "flavio@flavio.com")
						)
				);
		
	}
	
	

	
	
	@PostConstruct
	public void intiProductos() {
		listaProductosDefecto.addAll(Arrays.asList(
				new Producto("Mordedor-A", 20.0, 1, "../static/pesa.jpg"),
				new Producto("Mordedor-B", 15.0, 1, "../static/pesa2.jpg"),
				new Producto("Mordedor-C", 10.0, 1, "../static/pesa3.jpg")));
	}
	
	
	public ArrayList<Producto> findAll() {
		
		return listaProductosDefecto;
		
		
	}
	
	
	
}

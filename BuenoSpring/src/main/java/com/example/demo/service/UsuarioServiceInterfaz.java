package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Usuario;

public interface UsuarioServiceInterfaz {
	
	
	public Usuario add(Usuario usuario);
	
	public List<Usuario> findAll();
	public Usuario findById(String id);
	public Usuario edit(Usuario usuario);

}

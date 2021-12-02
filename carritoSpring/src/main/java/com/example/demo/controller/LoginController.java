package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

public class LoginController {

	
	
	@Autowired
	private UsuarioService userServ;
	
	//vincular con página de inicio
	@GetMapping("/index.html")
	public void login() {
		
	}
	
	//falta return
	@PostMapping("/index.html")
	public void inicioSesion(@ModelAttribute("usuario") Usuario usuario) {
		userServ.compruebaUsuario(usuario.getNombre(), usuario.getPassword());
		
		
		
	}
	
	
}

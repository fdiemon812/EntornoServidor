package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class LoginController {

	
	
	@Autowired
	private UsuarioService userServ;
	
	//vincular con página de inicio
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());

	return "login";	
	}
	
	
	@PostMapping("/login/submit")
	public String envio(@ModelAttribute("usuario") Usuario usuario) {
		userServ.compruebaUsuario(usuario.getUser(), usuario.getPassword());
		System.out.println(usuario.getPassword());
		System.out.println(usuario.getUser());
		return "submit";
		
	}
	//falta return
//	@PostMapping("/index.html")
//	public String inicioSesion(@ModelAttribute("usuario") Usuario usuario) {
//		System.out.println(userServ.compruebaUsuario(usuario.getNombre(), usuario.getPassword()));
//		System.out.println(usuario.getPassword());
//		
//		return "index";
//		
//	}
	
	
}

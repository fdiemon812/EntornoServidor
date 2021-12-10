package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private UsuarioService userServ;
	
	//vincular con página de inicio
	@GetMapping({"/", "/login"})
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());

	return "login";	
	}
	
	
	@PostMapping("/login/catalogo")
	public String envio(@ModelAttribute("usuario") Usuario usuario, Model model) {
		
		model.addAttribute("listaProductos", userServ.findAll());
		model.addAttribute("producto", new Producto());

		System.out.println("postCatalogo");
		System.out.println(sesion.getAttribute("user"));

		String result="catalogo";
		boolean isUser= userServ.compruebaUsuario(usuario.getUser(), usuario.getPassword());
		
		if(isUser) {
			sesion.setAttribute("user", usuario.getUser());
			sesion.setMaxInactiveInterval(10);
		}else {
			result="redirect:/login";
		}
		
		return result;
		
	}
	
	
	@PostMapping("/login/pedido")
	public String resumenPedido(@ModelAttribute("producto") Producto producto) {
		System.out.println(producto.getNombre()+producto.getCantidad());

//		userServ.addPedido(0, pedido, producto)
		
		return "";
		
	}
	
	
	@GetMapping({"/login/catalogo","/login/catalogo/"})
	public String forzarInicio(@ModelAttribute("usuario") Usuario usuario) {
		System.out.println("getCatalogo");
		System.out.println(sesion.getAttribute("user"));

	
		
		return "redirect:/login";
		
	}
	
	
	
}

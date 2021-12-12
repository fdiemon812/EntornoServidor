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
import com.example.demo.service.PedidoService;
import com.example.demo.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private HttpSession sesion;
	
	@Autowired
	private UsuarioService userServ;
	
	@Autowired
	private PedidoService pedService;
	
	
	
	//FALTA ARREGLAR SESIONES, SELECCION DE ENVIO, VALIDACIONES, LISTAR PEDIDOS, ARREGLAR LOS LOGIN, 
	
	//	CANTIDADES INFERIORES A 0
	
	
	//vincular con página de inicio
	@GetMapping({"/", "/login"})
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());

	return "login";	
	}
	
	@PostMapping("/login/seleccion")
	public String seleccion(@ModelAttribute("usuario") Usuario usuario, Model model) {
		

		String result="seleccion";
		boolean isUser= userServ.compruebaUsuario(usuario.getUser(), usuario.getPassword());
		
		if(isUser) {
			
			Usuario userLogado = userServ.obtenerUsuario(usuario.getUser());
			
			sesion.setAttribute("usuario", usuario.getUser());
			sesion.setAttribute("pedido", new Pedido());
		}else {
			result="redirect:/login";
		}
		
		return result;
		
	}
	

	
	
	@PostMapping("/login/catalogo")
	public String resumenPedido(@ModelAttribute("producto2") Producto producto, Model model) {
		
		String result="catalogo";
		System.out.println("el usuario es"+sesion.getAttribute("usuario"));
		if(sesion.getAttribute("usuario")==null || sesion.getAttribute("pedido")==null){
			sesion.invalidate();
			result="redirect:/login";
		}else {
			
			model.addAttribute("listaProductos", pedService.findAll());
			model.addAttribute("producto2", new Producto());
			
			System.out.println(sesion.getAttribute("pedido"));
			Pedido pedido = (Pedido) sesion.getAttribute("pedido");
			pedService.addPedido(producto.getId(), pedido, producto.getCantidad());
			sesion.setAttribute("pedido", pedido);
			System.out.println(pedido);
		}
		
		return result;
		
	}
	
	@PostMapping("/login/resumen")
	public String resumenPedido(Model model) {
		
		String result="resumen";
		
		if(sesion.getAttribute("usuario")==null || sesion.getAttribute("pedido")==null){
			sesion.invalidate();
			result="redirect:/login";
		
		}else {
			
		
		Pedido pedido = (Pedido) sesion.getAttribute("pedido");
		model.addAttribute("listaPedido", pedido.getListaProductos());
		
		
		}
		return result;
	}
	
	
	@GetMapping({"/login/catalogo","/login/catalogo/", "/login/seleccion" , "/login/resumen"})
	public String forzarInicio(@ModelAttribute("usuario") Usuario usuario) {
		
		return "redirect:/login";
		
	}
	
	
	
}

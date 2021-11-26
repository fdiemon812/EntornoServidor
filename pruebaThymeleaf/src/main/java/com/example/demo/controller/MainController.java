package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.StudentService;

@Controller
public class MainController {
	
	@Autowired
	private StudentService servicio;
	

	@GetMapping("/attributeexample") //ruta para el navegador
	public String simpleAttributeExample(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		model.addAttribute("serverTime", dateFormat.format(new Date()));
		return "attributeexample"; //html que devuelve
	}
	
	@GetMapping("/list")
	public String studentList(Model model) {
		model.addAttribute("listaEstudiantes", servicio.findAll());
		return "list";
	}
	
	
}

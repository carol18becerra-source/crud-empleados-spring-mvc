package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
	
	
	private final EmpleadoService empleadoService;
		
	
	@GetMapping("/listar")
	public String listarEmpleados(Model model) {
		
		model.addAttribute("empleados", empleadoService.getAllEmpleados());
		
		return "listadoEmpleados";
	}

}

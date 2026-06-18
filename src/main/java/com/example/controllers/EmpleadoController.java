package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Empleado;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

	private static final Logger LOG = Logger.getLogger("EmpleadoController");

	private final EmpleadoService empleadoService;
	private final DepartamentoService departamentoService;

	@GetMapping("/listar")
	public String listarEmpleados(Model model) {

		model.addAttribute("empleados", empleadoService.getAllEmpleados());

		return "listadoEmpleados";
	}

	// metodo para mostrar el formulario de creacion de empleado
	@GetMapping("/alta")

	public String mostrarFormularioAlta(Model model) {

		// se necesitan los departamentos desde la capa de servicios
		model.addAttribute("departamentos", departamentoService.getAllDepartamentos());

		// se necesita enviar un objeto empleado vacio para que se vinculen
		// sus propiedades con cada control (elemento input, select, etc) del formulario
		model.addAttribute("empleado", new Empleado());

		return "formularioAltaModificacion";

	}

	@PostMapping("/persistir")
	// metodo para recibir los datos del formulario de creacion de empleados
	public String procesarFormularioAltaModificacion(@ModelAttribute Empleado empleado,
			@RequestParam String numerosTelefono,
			@RequestParam String direccionesCorreo) {

		LOG.info("Objeto empleado recibido: ");
		LOG.info(empleado.toString());
		LOG.info("Numeros de telefonos recibidos: " + numerosTelefono);
		LOG.info("Direcciones de correos recibidas: " + direccionesCorreo);

		// se recibe un objeto empleado con los datos del formulario
		// se envia a la capa de servicios para que lo guarde en la BD
		// empleadoService.saveEmpleado(empleado);

		return "redirect:/empleados/listar";

	}
}

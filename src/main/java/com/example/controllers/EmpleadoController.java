package com.example.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Correo;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
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
			@RequestParam String numerosTelefono, @RequestParam String direccionesCorreo) {

		LOG.info("Objeto empleado recibido: ");
		LOG.info(empleado.toString());
		LOG.info("Numeros de telefonos recibidos: " + numerosTelefono);
		LOG.info("Direcciones de correos recibidas: " + direccionesCorreo);

		// Hay que procesar los datos de los telefonos y correos que vienen en un string
		// separados por comas, y convertirlos en listas de objetos telefonos y correo
		// para luego agregarlos al objeto Empleado antes de persistirlo en la BD

		Set<Telefono> telefonos = new HashSet<Telefono>();

		if (!numerosTelefono.isEmpty() && !numerosTelefono.isBlank()) {
			String[] arrayNumerosTelefono = numerosTelefono.split(";");
			List<String> listadoNumeros = Arrays.asList(arrayNumerosTelefono);
			listadoNumeros.forEach(numero -> {
				telefonos.add(Telefono.builder().numero(numero).empleado(empleado).build());
			});
			
			empleado.setTelefonos(telefonos);

		}
		
		Set<Correo> correos = new HashSet<Correo>();

		if (!direccionesCorreo.isEmpty() && !direccionesCorreo.isBlank()) {
			String[] arrayDireccionesCorreo = direccionesCorreo.split(";");
			List<String> listadoCorreos = Arrays.asList(arrayDireccionesCorreo);
			listadoCorreos.forEach(direcciones -> {
				correos.add(Correo.builder().email(direccionesCorreo).empleado(empleado).build());
			});
			
			
			
			empleado.setEmails(correos);

		}

		// se recibe un objeto empleado con los datos del formulario
		// se envia a la capa de servicios para que lo guarde en la BD
		 empleadoService.saveEmpleado(empleado);

		return "redirect:/empleados/listar";

	}
}

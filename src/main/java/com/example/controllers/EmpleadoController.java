package com.example.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Correo;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;

import jakarta.validation.Valid;
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

	public String mostrarFormularioAlta(Model model, @ModelAttribute Empleado empleado) {

		// se necesitan los departamentos desde la capa de servicios
		model.addAttribute("departamentos", departamentoService.getAllDepartamentos());

		// se necesita enviar un objeto empleado vacio para que se vinculen
		// sus propiedades con cada control (elemento input, select, etc) del formulario

		// el codigo siguiente se comenta porque el objeto se pasa como al modelo a
		// traves de la anotacion @modelAttribute que se recebe como parametro de
		// metodo
		// model.addAttribute("empleado", new Empleado());

		return "formularioAltaModificacion";

	}

	// Método para recibir los datos del formulario de creación de empleado
	@PostMapping("/persistir")
	@SuppressWarnings({"LoggerStringConcat", "UseSpecificCatch", "CallToPrintStackTrace"})
	public String procesarFormularioAltaModificacion(
			@Valid @ModelAttribute Empleado empleado,
			BindingResult result,
			@RequestParam String numerosTelefono,
			@RequestParam String direccionesCorreo,
			Model model,
			@RequestParam(name = "file", required = false) MultipartFile file) {

		// Comprobar si hay errores en la informacion procedente del formulario
		if (result.hasErrors()) {

			model.addAttribute("departamentos",
					departamentoService.getAllDepartamentos());

			return "formularioAltaModificacion";
		}

		// preguntar si me han enviado foto para el empleado y si es asi
		// guardar el nombre de la foto en la propiedad, atributo, o variable miembro de
		// la clase, foto, y guardas el contenido de la foto como un archivo en el
		// sistema de archivos (file system) del servidor

		if (file != null && !file.isEmpty()){

			Path rutaRelativa = Paths.get("src/main/resources/static/imagenes");

			String rutaAbsoluta = rutaRelativa.toFile().getAbsolutePath();

			Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + file.getOriginalFilename());

			try {
				byte[] bytesFotoRecibida = file.getBytes();
				Files.write(rutaCompleta, bytesFotoRecibida);
				empleado.setFoto(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		LOG.info("Objeto empleado recibido: ");
		LOG.info(empleado.toString());
		LOG.info("Numeros de telefonos recibidos: " + numerosTelefono);
		LOG.info("Direcciones de correos recibidas: " + direccionesCorreo);

		// Hay que procesar los datos de los telefonos y correos que vienen en un string
		// separados por comas, y convertirlos en listas de objetos telefonos y correo
		// para luego agregarlos al objeto Empleado antes de persistirlo en la BD

		// Set<Telefono> telefonos = new HashSet<Telefono>();

		if (!numerosTelefono.isEmpty() && !numerosTelefono.isBlank()) {

			String[] arrayNumerosTelefono = numerosTelefono.split(";");
			List<String> listadoNumeros = Arrays.asList(arrayNumerosTelefono);

			listadoNumeros.forEach(numero -> {
				empleado.getTelefonos().add(Telefono.builder().numero(numero).empleado(empleado).build());
			});

			// empleado.setTelefonos(telefonos);
		}

		if (!direccionesCorreo.isEmpty() && !direccionesCorreo.isBlank()) {

			String[] arrayDirCorreos = direccionesCorreo.split(";");
			List<String> listadoCorreos = Arrays.asList(arrayDirCorreos);

			listadoCorreos.forEach(dirCorr -> {
				empleado.getEmails().add(Correo.builder()
						.email(dirCorr).empleado(empleado).build());
			});
		}

		// se recibe un objeto empleado con los datos del formulario
		// se envia a la capa de servicios para que lo guarde en la BD
		empleadoService.saveEmpleado(empleado);

		return "redirect:/empleados/listar";

	}

 //Método que muestra los detalles de un empleado cuyo id se recibe como parámetro
   @GetMapping("/details/{id}")
   public String mostrarDetalles (Model model,
       @PathVariable(name = "id", required = true) int empleado_id){


   //recuperar el empleado cuyo id se recibe como parametro
   model.addAttribute("empleado", empleadoService.getEmpleadoById(empleado_id));


       return "details";
   }


}


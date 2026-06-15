package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.model.Genero;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEmpleadosSpringMvcApplication implements CommandLineRunner {

	private final EmpleadoService empleadoService;
	private final DepartamentoService departamentoService;
	private final CorreoService correoService;
	private final TelefonoService telefonoService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEmpleadosSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Crear registros de ejemplo en la base de datos, lo
		// cual nos permite comprobrar que la aplicación funciona correctamente.
		// Concretamente la capa de servicio y la capa de persistencia.

		// crear departamentos

		Departamento departemento1 = Departamento.builder().nombre("RRHH").build();

		Departamento departamento2 = Departamento.builder().nombre("IT").build();

		Departamento departamento3 = Departamento.builder().nombre("Marketing").build();

		Departamento departamento4 = Departamento.builder().nombre("Ventas").build();

		Departamento departamento5 = Departamento.builder().nombre("Informática").build();

		// persistir los departamentos en la base de datos

		departamentoService.saveDepartamento(departemento1);
		departamentoService.saveDepartamento(departamento2);
		departamentoService.saveDepartamento(departamento3);
		departamentoService.saveDepartamento(departamento4);
		departamentoService.saveDepartamento(departamento5);

		// // crear empleados
		Empleado empleado1 = Empleado.builder().nombre("Juan").primerApellido("Pérez").segundoApellido("García")
				.genero(Genero.HOMBRE).fechaAlta(LocalDate.of(2023, 01, 01)).departamento(departemento1)
				.salario(new BigDecimal("2500.00"))
				.telefonos(Set.of(Telefono.builder().numero("123456789").build(),
						Telefono.builder().numero("987654321").build()))
				.emails(Set.of(Correo.builder().email("empl@g.com").build(),
						Correo.builder().email("empl@gg.com").build()))
				.build();

		// antes de persistir el empleado, para que en las tablas de correos y telefonos
		// el campo empleado_id no sea nulo,
		// hay que establecer la relación entre el empleado y sus correos y teléfonos.
		empleado1.getTelefonos().forEach(telefono -> telefono.setEmpleado(empleado1));
		empleado1.getEmails().forEach(correo -> correo.setEmpleado(empleado1));

		empleadoService.saveEmpleado(empleado1);
	}

}

package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.Empleado;
import java.util.List;
import com.example.model.Genero;


public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
	
	/* Para generar metodos, ademas de los que ya se tienen por defecto en la interfaces
	 * de las cuales hereda JpaRepository hay que hacer suministrando la sintaxis correcta
	 * como se indica en los enlaces siguientes:
	 * 
	 * Para aprender, que no es el sitio oficial, pero es mas didactico:
	 * 
	 * https://www.baeldung.com/spring-data-derived-queries
	 * 
	 * 
	 * Oficial: 
	 * 
	 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html 
	 * 
	 * Buscar JPA Query Methods */
 
	
}

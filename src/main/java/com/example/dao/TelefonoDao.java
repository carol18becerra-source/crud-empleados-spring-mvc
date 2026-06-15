package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Telefono;
import com.example.entities.Empleado;
import java.util.List;



public interface TelefonoDao extends JpaRepository<Telefono, Integer> {
	
	boolean existsByEmpleado(Empleado empleado);
	void deleteByEmpleado(Empleado empleado);
	List<Telefono> findByEmpleado(Empleado empleado);
}

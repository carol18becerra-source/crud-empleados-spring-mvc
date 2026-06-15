package com.example.services;

import java.util.List;


import com.example.entities.Empleado;
import com.example.entities.Telefono;

public interface TelefonoService {
 
	List<Telefono> getAllTelefonos();
	Telefono saveTelefono(Telefono telefono);
	boolean existsByEmpleado(Empleado empleado);
	void deleteByEmpleado(Empleado empleado);
	List<Telefono> findByEmpleado(Empleado empleado);
}

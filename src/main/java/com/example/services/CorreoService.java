package com.example.services;

import java.util.List;

import com.example.entities.Correo;
import com.example.entities.Empleado;

public interface CorreoService {
	
	Correo saveCorreo(Correo correo);
	List<Correo> getAllCorreos();
	boolean existsByEmpleado(Empleado empleado);
	void deleteByEmpleado(Empleado empleado);
	List<Correo> findByEmpleado(Empleado empleado);
}

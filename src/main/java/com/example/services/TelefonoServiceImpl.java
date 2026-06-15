package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.TelefonoDao;
import com.example.entities.Empleado;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class TelefonoServiceImpl implements TelefonoService {
	
	private final TelefonoDao telefonoDao;

	@Override
	public List<Telefono> getAllTelefonos() {
		// TODO Auto-generated method stub
		return telefonoDao.findAll();
	}

	@Override
	public Telefono saveTelefono(Telefono telefono) {
		// TODO Auto-generated method stub
		return telefonoDao.save(telefono);
	}

	@Override
	public boolean existsByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return telefonoDao.existsByEmpleado(empleado);
	}

	@Override
	public void deleteByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		telefonoDao.deleteByEmpleado(empleado);
		
	}

	@Override
	public List<Telefono> findByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return telefonoDao.findByEmpleado(empleado);
	}

}

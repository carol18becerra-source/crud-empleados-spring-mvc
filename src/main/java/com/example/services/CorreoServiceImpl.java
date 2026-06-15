package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.CorreoDao;
import com.example.entities.Correo;
import com.example.entities.Empleado;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CorreoServiceImpl implements CorreoService {
	
	private final CorreoDao correoDao;

	@Override
	public Correo saveCorreo(Correo correo) {
		// TODO Auto-generated method stub
		return correoDao.save(correo);
	}

	@Override
	public List<Correo> getAllCorreos() {
		// TODO Auto-generated method stub
		return correoDao.findAll();
	}

	@Override
	public boolean existsByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return correoDao.existsByEmpleado(empleado);
	}

	@Override
	public void deleteByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		correoDao.deleteByEmpleado(empleado);
		
	}

	@Override
	public List<Correo> findByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return correoDao.findByEmpleado(empleado);
	}
	

}

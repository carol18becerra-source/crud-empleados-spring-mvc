package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DepartamentoServiceImpl  implements DepartamentoService {
	
	
	private final DepartamentoDao departamentoDao;

	@Override
	public Departamento saveDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		return departamentoDao.save(departamento);
	}

	@Override
	public List<Departamento> getAllDepartamentos() {
		// TODO Auto-generated method stub
		return departamentoDao.findAll();
	}

	

}

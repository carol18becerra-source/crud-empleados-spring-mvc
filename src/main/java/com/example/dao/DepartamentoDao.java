package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Departamento;

public interface DepartamentoDao extends JpaRepository<Departamento, Integer> {

}

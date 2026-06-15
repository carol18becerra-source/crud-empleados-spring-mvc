package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Correo;

public interface CorreoDao extends JpaRepository<Correo, Integer> {

}

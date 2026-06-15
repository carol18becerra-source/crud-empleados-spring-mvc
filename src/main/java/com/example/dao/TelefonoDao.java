package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Telefono;

public interface TelefonoDao extends JpaRepository<Telefono, Integer> {

}

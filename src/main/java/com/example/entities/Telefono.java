package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="telefonos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Telefono {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado empleado;
}

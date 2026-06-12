package com.example.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="departamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Departamento {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nombre;

    /**
     * Las relaciones entre las entidades en JPA (Java Persistence API) son bidireccionales,
     * a diferencia de las relaciones en SQL que son unidireccionales, es decir, que una 
     * entidad hija sabe quien es su padre, porque en dicha entidad es donde se crea la relacion
     * de clave externa (FK), pero la entidad padre no sabe que tiene hijo(s)
     */

    /* El atributo mappedBy apunta a una propiedad en el lado de muchos de la relacion, pues
    aunque las relaciones son bidireccionales hay que especificar donde se va a crear 
    la relacion de clave externa, que al igual que en SQL es en el lado de muchos */
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "departamento")
    private List<Empleado> empleados;
}

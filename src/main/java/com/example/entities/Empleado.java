package com.example.entities;

import java.io.Serializable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.model.Genero;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "empleados")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@ToString(exclude = { "telefonos", "emails" })
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El nombre no puede estar vacio")
    @NotBlank(message = "El nombre no puede contener espacios en blanco, solamente")
    @Size(min = 4, max = 30, message = "El nombre tiene que estar entre 4 y 30 caracteres")
    @Pattern(regexp = "^([A-ZÁÉÍÓÚÑ]{1}[a-záéíóúñ]*\s?)+$", message = "El nombre debe empezar con mayúscula, solo contener letras y puede incluir espacios intermedios")
    private String nombre;

    @NotNull(message = "El primer apellido no puede estar vacio")
    @NotBlank(message = "El primer apellido no puede contener espacios en blanco, solamente")
    @Size(min = 4, max = 30, message = "El primer apellido tiene que estar entre 4 y 30 caracteres")
    @Pattern(regexp = "^([A-ZÁÉÍÓÚÑ]{1}[a-záéíóúñ]*\s?)+$", message = "El Primer Apellido debe empezar con mayúscula, solo contener letras y puede incluir espacios intermedios")

    private String primerApellido;

    @NotNull(message = "El segundo apellido no puede estar vacio")
    @NotBlank(message = "El segundo apellido no puede contener espacios en blanco, solamente")
    @Size( max = 30, message = "El segundo apellido no puede superar los 45 caracteres")
    @Pattern(regexp = "^([A-ZÁÉÍÓÚÑ]{1}[a-záéíóúñ]*\s?)+$", message = "El Segundo Apellido debe empezar con mayúscula, solo contener letras y puede incluir espacios intermedios")
    private String segundoApellido;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de alta es obligatoria")
    @Past(message = "La fecha de alta no puede ser posterior al día de hoy")
    private LocalDate fechaAlta;

    @NotNull(message = "El salario es obligatorio")
    @Min(value = 1000, message = "El salario no puede ser inferior o igual a 1000")
    private BigDecimal salario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
    private Set<Telefono> telefonos = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
    private Set<Correo> emails = new HashSet<>();

}

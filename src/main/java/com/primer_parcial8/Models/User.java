package com.primer_parcial8.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @Column(length = 300, nullable = false)
    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellidos;
    @Column(length = 20, nullable = false, unique = true)
    @NotBlank(message = "El documento no puede estar vacio y debe ser unico")
    private String documento;
    @Column(length = 100)
    private String direccion;
    private Date fechaNacimiento;
    @Column(length = 20)
    private String telefono;
    @Column(unique = true, length = 64, nullable = false)
    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacio")
    private String password;
}

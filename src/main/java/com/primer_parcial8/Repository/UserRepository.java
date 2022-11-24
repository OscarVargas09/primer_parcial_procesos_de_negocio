package com.primer_parcial8.Repository;

import com.primer_parcial8.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByNombre (String nombre);
    List<User> findAllByApellidos(String apellidos);
    List<User> findByNombreAndApellidos(String nombre, String apellidos);
    User findByCorreo (String correo);
}
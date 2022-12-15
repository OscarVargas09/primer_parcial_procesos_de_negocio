package com.primer_parcial8.Services;

import com.primer_parcial8.Models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<User> getUserById(Long id);
    ResponseEntity <User> createUser(User user);
    ResponseEntity <List<User>> allUsers();
    ResponseEntity <List<User>> allUsersByNameAndLastname(String nombre, String apellidos);
    ResponseEntity <List<User>> allUsersByName(String nombre);
    ResponseEntity <List<User>> allUsersByLastname(String apellidos);
    ResponseEntity <User> editUser(Long id, User user);
    ResponseEntity <User> deleteUserById(Long id);

    ResponseEntity getUserByCorreo(String correo);

    ResponseEntity login(String correo, String password);
}

package com.primer_parcial8.Services;

import com.primer_parcial8.Models.User;
import com.primer_parcial8.Repository.UserRepository;
import com.primer_parcial8.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        try{
            user.setPassword((passwordEncoder.encode(user.getPassword())));
            userRepository.save(user);
            return new ResponseEntity(user, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByNameAndLastname(String nombre, String apellidos) {
        List<User> users = userRepository.findByNombreAndApellidos(nombre,apellidos);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByName(String nombre) {
        List<User> users = userRepository.findAllByNombre(nombre) ;
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByLastname(String apellidos) {
        List<User> users = userRepository.findAllByApellidos(apellidos);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> editUser(Long id, User user) {
        Optional<User> userBD = userRepository.findById(id);
        if(userBD.isPresent()){
            try{
                userBD.get().setNombre((user.getNombre()));
                userBD.get().setApellidos((user.getApellidos()));
                userBD.get().setDocumento((user.getDocumento()));
                userBD.get().setFechaNacimiento((user.getFechaNacimiento()));
                userBD.get().setTelefono((user.getTelefono()));
                userBD.get().setDireccion((user.getDireccion()));
                userBD.get().setCorreo((user.getCorreo()));
                userBD.get().setPassword((user.getPassword()));
                userBD.get().setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(userBD.get());
                return new ResponseEntity(userBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }


        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> deleteUserById(Long id) {
        Optional<User> userBD = userRepository.findById(id);
        if(userBD.isPresent()){
            userRepository.delete(userBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity login(String correo, String password) {
        try{
            User user = userRepository.findByCorreo(correo);
            if(passwordEncoder.matches(password, user.getPassword())){
                String token = jwtUtil.create(String.valueOf(user.getId()), user.getCorreo());
                return ResponseEntity.ok(token);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }
}


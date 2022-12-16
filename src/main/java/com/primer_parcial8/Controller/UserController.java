package com.primer_parcial8.Controller;

import com.primer_parcial8.Models.User;
import com.primer_parcial8.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.primer_parcial8.Services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity getUser(@PathVariable Long id,
                                     @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity listUser(
            @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return userService.allUsers();
    }

    @GetMapping("/user/{nombre}/{apellido}")
    public ResponseEntity listByNameLastname(@PathVariable String nombre,
                                                  @PathVariable String apellidos,
                                                  @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return userService.allUsersByNameAndLastname(nombre, apellidos);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listByName(@PathVariable String nombre,
                                          @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return userService.allUsersByName(nombre);
    }

    @GetMapping("/usuario/apellidos/{apellidos}")
    public ResponseEntity listByLastName(@PathVariable String apellidos,
                                             @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return userService.allUsersByLastname(apellidos);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity updateUsuario(@PathVariable Long id,
                                        @Valid @RequestBody User user,
                                        @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return userService.editUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id,
                                          @RequestHeader(value = "Authorization")String token) {
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return userService.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

package com.primer_parcial8.Controller;

import com.primer_parcial8.Models.User;
import com.primer_parcial8.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class AuthController {

        @Autowired
        private UserService userService;

        @PostMapping(value = "/auth/login")
        public ResponseEntity login (@RequestBody User user){
            return userService.login(user.getCorreo(), user.getPassword());
        }
    }


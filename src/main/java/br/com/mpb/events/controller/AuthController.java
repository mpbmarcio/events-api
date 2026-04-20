package br.com.mpb.events.controller;

import br.com.mpb.events.model.User;
import br.com.mpb.events.security.JwtToken;
import br.com.mpb.events.service.UserImpService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserImpService service;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserImpService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User validUser = service.getUserByEmail(user.getEmail());

        if (validUser != null && passwordEncoder.matches(user.getPassword(), validUser.getPassword())) {
            return ResponseEntity.ok(JwtToken.generateToken(validUser.getEmail()));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}

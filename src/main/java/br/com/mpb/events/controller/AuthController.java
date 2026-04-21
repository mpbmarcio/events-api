package br.com.mpb.events.controller;

import br.com.mpb.events.model.User;
import br.com.mpb.events.security.JwtToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtToken jwtToken;

    public AuthController(AuthenticationManager authenticationManager, JwtToken jwtToken) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtToken.generateToken(user.getEmail()));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}

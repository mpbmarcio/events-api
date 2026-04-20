package br.com.mpb.events.controller;

import br.com.mpb.events.dto.request.ChangePasswordUserRequestDTO;
import br.com.mpb.events.dto.response.UserResponseDTO;
import br.com.mpb.events.model.User;
import br.com.mpb.events.service.IUserService;
import br.com.mpb.events.service.UserImpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private IUserService service;
    private PasswordEncoder passwordEncoder;

    public UserController(UserImpService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(service.addUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        return ResponseEntity.ok(service.getAllUser());
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordUserRequestDTO request) {
        User user = service.getUserByEmail(request.email());

        if (user != null && passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.newPassword()));
            service.update(user);
            return ResponseEntity.ok("Senha alterada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha atual inválida");
        }
    }
}

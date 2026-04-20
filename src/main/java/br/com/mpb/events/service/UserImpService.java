package br.com.mpb.events.service;

import br.com.mpb.events.dto.response.UserResponseDTO;
import br.com.mpb.events.exception.NotFoundException;
import br.com.mpb.events.model.User;
import br.com.mpb.events.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpService implements IUserService{

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserImpService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return new UserResponseDTO(user.getUserId(), user.getName(), user.getEmail());
    }

    @Override
    public User getUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " not registered"));
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        return repository.findAll()
                .stream()
                .map(u -> new UserResponseDTO(u.getUserId(), u.getName(), u.getEmail()))
                .toList();
    }

    @Override
    public User validateLogin(String email, String password) {
        return repository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }

    @Override
    public User update(User user) {
        User existingUser = repository.findById(user.getUserId())
                .orElseThrow(() -> new NotFoundException("User " + user.getUserId() + " not found"));

        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        return repository.save(existingUser);
    }

}

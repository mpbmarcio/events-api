package br.com.mpb.events.service;

import br.com.mpb.events.dto.response.UserResponseDTO;
import br.com.mpb.events.model.User;

import java.util.List;

public interface IUserService {
    public UserResponseDTO addUser(User user);
    public User getUserById(Integer id);
    public User getUserByEmail(String email);
    public List<UserResponseDTO> getAllUser();
    User validateLogin(String email, String password);
    User update(User user);
}

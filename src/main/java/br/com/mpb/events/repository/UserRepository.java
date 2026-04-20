package br.com.mpb.events.repository;

import br.com.mpb.events.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Integer> {

    public Optional<User> findByEmail(String email);
}

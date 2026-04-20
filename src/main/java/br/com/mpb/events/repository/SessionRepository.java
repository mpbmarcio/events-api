package br.com.mpb.events.repository;

import br.com.mpb.events.model.Session;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface SessionRepository extends ListCrudRepository<Session, Integer> {
}

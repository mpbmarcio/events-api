package br.com.mpb.events.repository;

import br.com.mpb.events.model.Conference;
import org.springframework.data.repository.ListCrudRepository;

public interface ConferenceRepository extends ListCrudRepository<Conference, Integer> {
}

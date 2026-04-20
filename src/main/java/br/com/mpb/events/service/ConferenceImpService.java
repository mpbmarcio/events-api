package br.com.mpb.events.service;

import br.com.mpb.events.exception.NotFoundException;
import br.com.mpb.events.model.Conference;
import br.com.mpb.events.repository.ConferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceImpService implements IConferenceService{

    private ConferenceRepository repository;

    public ConferenceImpService(ConferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Conference addConference(Conference conference) {
        return repository.save(conference);
    }

    @Override
    public Conference getConferenceById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Conference " + id + " not found"));
    }

    @Override
    public List<Conference> getAllConferences() {
        return repository.findAll();
    }
}

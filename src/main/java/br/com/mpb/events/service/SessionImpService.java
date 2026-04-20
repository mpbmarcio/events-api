package br.com.mpb.events.service;

import br.com.mpb.events.exception.NotFoundException;
import br.com.mpb.events.model.Session;
import br.com.mpb.events.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionImpService implements ISessionService{

    private SessionRepository repository;

    public SessionImpService(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Session addSession(Session session) {
        return repository.save(session);
    }

    @Override
    public Session getSessionById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Session " + id + " not found."));
    }

    @Override
    public List<Session> getAllSessions() {
        return repository.findAll();
    }
}

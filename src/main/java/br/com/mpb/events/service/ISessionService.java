package br.com.mpb.events.service;

import br.com.mpb.events.model.Session;

import java.util.List;
import java.util.Optional;

public interface ISessionService {
    public Session addSession(Session session);
    public Session getSessionById(Integer id);
    public List<Session> getAllSessions();
}

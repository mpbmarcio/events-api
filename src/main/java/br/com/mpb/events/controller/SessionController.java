package br.com.mpb.events.controller;

import br.com.mpb.events.model.Session;
import br.com.mpb.events.service.ISessionService;
import br.com.mpb.events.service.SessionImpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SessionController {

    private ISessionService service;

    public SessionController(SessionImpService service) {
        this.service = service;
    }

    @PostMapping("/sessions")
    public ResponseEntity<Session> addSession(@RequestBody Session session) {
        return ResponseEntity.status(201).body(service.addSession(session));
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(service.getAllSessions());
    }
}

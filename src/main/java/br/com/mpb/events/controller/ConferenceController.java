package br.com.mpb.events.controller;

import br.com.mpb.events.model.Conference;
import br.com.mpb.events.service.ConferenceImpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConferenceController {

    private ConferenceImpService service;

    public ConferenceController(ConferenceImpService service) {
        this.service = service;
    }

    @PostMapping("/conferences")
    public ResponseEntity<Conference> addConference(@RequestBody Conference conference) {
        return ResponseEntity.status(201).body(service.addConference(conference));
    }

    @GetMapping("/conferences")
    public ResponseEntity<List<Conference>> getAllConferences() {
        return ResponseEntity.ok(service.getAllConferences());
    }

    @GetMapping("/conferences/id")
    public ResponseEntity<Conference> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getConferenceById(id));
    }
}

package br.com.mpb.events.service;

import br.com.mpb.events.dto.response.SubscriptionResponseDTO;
import br.com.mpb.events.dto.response.UserResponseDTO;
import br.com.mpb.events.model.Session;
import br.com.mpb.events.model.Subscription;
import br.com.mpb.events.model.User;
import br.com.mpb.events.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionImpService implements ISubscriptionService{

    @Autowired
    private SubscriptionRepository repository;

    @Override
    public Subscription addSubscription(Subscription subscription) {
        subscription.setCreatedAt(LocalDateTime.now());
        subscription.setUniqueID(UUID.randomUUID().toString());
        return repository.save(subscription);
    }

    @Override
    public List<Subscription> getAllByIdUser(User user) {
        return repository.findByIdUser(user);
    }

    @Override
    public List<Subscription> getAllByIdSession(Session session) {
        return repository.findByIdSession(session);
    }

    @Override
    public List<SubscriptionResponseDTO> getAllConferences() {
        return repository.findAll()
                .stream()
                .map(s -> new SubscriptionResponseDTO(
                        new UserResponseDTO(
                                s.getId().getUser().getUserId(),
                                s.getId().getUser().getName(),
                                s.getId().getUser().getEmail()
                        ),
                s.getId().getSession(),
                s.getCreatedAt(),
                s.getLevel(),
                s.getUniqueID()
                ))
                .toList();
    }
}

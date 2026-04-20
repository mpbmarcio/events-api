package br.com.mpb.events.service;

import br.com.mpb.events.dto.response.SubscriptionResponseDTO;
import br.com.mpb.events.model.Session;
import br.com.mpb.events.model.Subscription;
import br.com.mpb.events.model.User;

import java.util.List;

public interface ISubscriptionService {
    public Subscription addSubscription(Subscription subscription);
    public List<Subscription> getAllByIdUser(User user);
    public List<Subscription> getAllByIdSession(Session session);
    public List<SubscriptionResponseDTO> getAllConferences();
}

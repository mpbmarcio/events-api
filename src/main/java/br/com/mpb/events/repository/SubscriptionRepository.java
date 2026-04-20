package br.com.mpb.events.repository;

import br.com.mpb.events.model.Session;
import br.com.mpb.events.model.Subscription;
import br.com.mpb.events.model.SubscriptionID;
import br.com.mpb.events.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface SubscriptionRepository extends ListCrudRepository<Subscription, SubscriptionID> {
    public List<Subscription> findByIdUser(User user);
    public List<Subscription> findByIdSession(Session session);
}

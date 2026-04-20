package br.com.mpb.events.dto.response;

import br.com.mpb.events.model.Session;

import java.time.LocalDateTime;

public record SubscriptionResponseDTO(
        UserResponseDTO user,
        Session session,
        LocalDateTime createdAt,
        int level,
        String uniqueId
) {}

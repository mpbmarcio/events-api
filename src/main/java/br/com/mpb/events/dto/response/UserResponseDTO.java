package br.com.mpb.events.dto.response;

public record UserResponseDTO(
        Integer id,
        String name,
        String email
) {
}

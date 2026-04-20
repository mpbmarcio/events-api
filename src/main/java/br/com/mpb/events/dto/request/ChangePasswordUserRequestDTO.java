package br.com.mpb.events.dto.request;

public record ChangePasswordUserRequestDTO(
        String email,
        String oldPassword,
        String newPassword
) {}

package com.agencia.viajes.dto;

import java.util.List;

public record UserDTO(
        String firstName,
        String lastName,
        String email,
        boolean emailVerified,
        String username,
        boolean enabled,
        List<CredentialDTO> credentials
) {
    public record CredentialDTO(String type, String value, boolean temporary) {
    }
}

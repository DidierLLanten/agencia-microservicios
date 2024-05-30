package com.agencia.viajes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserSensibleDTO(String id,
                              String username,
                              String email,
                              boolean emailVerified,
                              boolean enabled
) {
}

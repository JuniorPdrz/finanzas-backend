package com.tuapp.finanzas.delivery.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CrearUsuarioRequest(
        @NotBlank String nombre,
        @Email @NotBlank String email,
        @NotBlank String password
) {}
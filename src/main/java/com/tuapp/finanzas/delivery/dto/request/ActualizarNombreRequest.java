package com.tuapp.finanzas.delivery.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ActualizarNombreRequest(
        @NotBlank String nombre
) {}
// delivery/dto/request/LoginRequest.java
package com.tuapp.finanzas.delivery.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email @NotBlank String email,
        @NotBlank String password
) {}
package com.tuapp.finanzas.auth.dto.response;

public record LoginResponse(String token, String tipo) {
    public static LoginResponse of(String token) {
        return new LoginResponse(token, "Bearer");
    }
}

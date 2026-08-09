package com.tuapp.finanzas.delivery.dto.response;

import com.tuapp.finanzas.domain.model.Usuario;

public record LoginResponse(String token, String tipo, UsuarioResponse usuario) {

    public static LoginResponse of(String token, Usuario usuario) {
        return new LoginResponse(token, "Bearer", UsuarioResponse.from(usuario));
    }
}

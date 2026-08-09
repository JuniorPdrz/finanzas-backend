package com.tuapp.finanzas.delivery.rest;

import com.tuapp.finanzas.delivery.dto.request.LoginRequest;
import com.tuapp.finanzas.delivery.dto.response.LoginResponse;
import com.tuapp.finanzas.delivery.dto.response.UsuarioResponse;
import com.tuapp.finanzas.domain.model.Usuario;
import com.tuapp.finanzas.domain.port.in.AutenticarUsuarioUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AutenticarUsuarioUseCase autenticarUseCase;

    public AuthController(AutenticarUsuarioUseCase autenticarUseCase) {
        this.autenticarUseCase = autenticarUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var resultado = autenticarUseCase.login(request.email(), request.password());
        return ResponseEntity.ok(LoginResponse.of(resultado.token(), resultado.usuario()));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> me(@AuthenticationPrincipal UUID usuarioId) {
        if (usuarioId == null) {
            return ResponseEntity.status(401).build();
        }
        Usuario usuario = autenticarUseCase.obtenerPorId(usuarioId);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }
}

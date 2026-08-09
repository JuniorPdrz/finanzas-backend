package com.tuapp.finanzas.auth.controller;

import com.tuapp.finanzas.delivery.dto.request.LoginRequest;
import com.tuapp.finanzas.delivery.dto.response.LoginResponse;
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
}
package com.tuapp.finanzas.auth.controller;

import com.tuapp.finanzas.auth.dto.request.LoginRequest;
import com.tuapp.finanzas.auth.dto.response.LoginResponse;
import com.tuapp.finanzas.auth.domain.port.in.AutenticarUsuarioUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AutenticarUsuarioUseCase autenticarUseCase;

    public AuthController(AutenticarUsuarioUseCase autenticarUseCase) {
        this.autenticarUseCase = autenticarUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        String token = autenticarUseCase.login(request.email(), request.password());
        return ResponseEntity.ok(LoginResponse.of(token));
    }
}

package com.tuapp.finanzas.delivery.rest;

import com.tuapp.finanzas.delivery.dto.request.LoginRequest;
import com.tuapp.finanzas.delivery.dto.response.LoginResponse;
import com.tuapp.finanzas.domain.port.in.AutenticarUsuarioUseCase;
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
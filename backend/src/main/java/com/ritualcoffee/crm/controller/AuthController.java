package com.ritualcoffee.crm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import com.ritualcoffee.crm.dto.LoginRequest;
import com.ritualcoffee.crm.dto.RegistroRequest;
import com.ritualcoffee.crm.dto.UsuarioResponse;
import com.ritualcoffee.crm.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // para permitir peticiones desde Angular
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        UsuarioResponse response = usuarioService.registrarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@Valid @RequestBody LoginRequest request) {
        UsuarioResponse response = usuarioService.login(request);
        return ResponseEntity.ok(response);
    }
}

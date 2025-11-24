package com.ritualcoffee.crm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.ritualcoffee.crm.dto.CrearUsuarioRequest;
import com.ritualcoffee.crm.dto.UsuarioDTO;
import com.ritualcoffee.crm.service.UsuarioService;

@RestController
@RequestMapping("/api/admin/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioAdminController {

    private final UsuarioService usuarioService;

    public UsuarioAdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Crear usuario (admin o cliente)
    @PostMapping
    public UsuarioDTO crear(@Valid @RequestBody CrearUsuarioRequest request) {
        return usuarioService.crearUsuarioComoAdmin(request);
    }

    // Listar todos los usuarios
    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listarUsuarios();
    }

    // Obtener un usuario concreto
    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Integer id) {
        return usuarioService.obtenerPorId(id);
    }

    // Actualizar usuario existente
    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Integer id,
    		@Valid @RequestBody CrearUsuarioRequest request) {
        return usuarioService.actualizarUsuario(id, request);
    }

    // Borrar usuario
    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Integer id) {
        usuarioService.borrarUsuario(id);
    }
}

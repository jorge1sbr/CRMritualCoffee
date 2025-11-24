package com.ritualcoffee.crm.service;

import java.util.List;

import com.ritualcoffee.crm.dto.CrearUsuarioRequest;
import com.ritualcoffee.crm.dto.LoginRequest;
import com.ritualcoffee.crm.dto.RegistroRequest;
import com.ritualcoffee.crm.dto.UsuarioDTO;
import com.ritualcoffee.crm.dto.UsuarioResponse;

public interface UsuarioService {

    // ===== AUTH (lo que ya tenías) =====
    UsuarioResponse registrarUsuario(RegistroRequest request);

    UsuarioResponse login(LoginRequest request);

    // ===== CRUD ADMIN =====

    // Crear usuario (cliente o admin) desde panel admin
    UsuarioDTO crearUsuarioComoAdmin(CrearUsuarioRequest request);

    // Listar todos los usuarios
    List<UsuarioDTO> listarUsuarios();

    // Obtener usuario por id
    UsuarioDTO obtenerPorId(Integer id);

    // Actualizar usuario por id
    UsuarioDTO actualizarUsuario(Integer id, CrearUsuarioRequest request);

    // Borrar usuario por id
    void borrarUsuario(Integer id);
}

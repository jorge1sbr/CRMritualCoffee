package com.ritualcoffee.crm.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ritualcoffee.crm.dto.CrearUsuarioRequest;
import com.ritualcoffee.crm.dto.LoginRequest;
import com.ritualcoffee.crm.dto.RegistroRequest;
import com.ritualcoffee.crm.dto.UsuarioDTO;
import com.ritualcoffee.crm.dto.UsuarioResponse;
import com.ritualcoffee.crm.entity.Rol;
import com.ritualcoffee.crm.entity.Usuario;
import com.ritualcoffee.crm.repository.UsuarioRepository;
import com.ritualcoffee.crm.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // ============================================================
    // ================      AUTH      ============================
    // ============================================================

    @Override
    @Transactional
    public UsuarioResponse registrarUsuario(RegistroRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setEmail(request.getEmail());
        usuario.setPasswordHash(request.getPassword()); // por ahora sin encriptar
        usuario.setDireccion(request.getDireccion());
        usuario.setCodigoPostal(request.getCodigoPostal());
        usuario.setRol(Rol.CLIENTE); 
        usuario.setFechaAlta(LocalDateTime.now());

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(guardado.getIdUsuario());
        response.setNombre(guardado.getNombre());
        response.setApellidos(guardado.getApellidos());
        response.setEmail(guardado.getEmail());
        response.setRol(guardado.getRol().name());
        response.setMensaje("Usuario registrado correctamente");

        return response;
    }

    @Override
    public UsuarioResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!usuario.getPasswordHash().equals(request.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getIdUsuario());
        response.setNombre(usuario.getNombre());
        response.setApellidos(usuario.getApellidos());
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol().name());
        response.setMensaje("Login correcto");

        return response;
    }

    // ============================================================
    // ================          CRUD ADMIN         ================
    // ============================================================

    @Override
    @Transactional
    public UsuarioDTO crearUsuarioComoAdmin(CrearUsuarioRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario u = new Usuario();
        u.setNombre(request.getNombre());
        u.setApellidos(request.getApellidos());
        u.setEmail(request.getEmail());
        u.setPasswordHash(request.getPassword()); // sin cifrar hasta meter seguridad
        u.setDireccion(request.getDireccion());
        u.setCodigoPostal(request.getCodigoPostal());
        u.setRol(request.getRol()); // ADMIN o CLIENTE
        u.setFechaAlta(LocalDateTime.now());

        return toDTO(usuarioRepository.save(u));
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public UsuarioDTO obtenerPorId(Integer id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDTO(u);
    }

    @Override
    @Transactional
    public UsuarioDTO actualizarUsuario(Integer id, CrearUsuarioRequest request) {

        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        u.setNombre(request.getNombre());
        u.setApellidos(request.getApellidos());
        u.setDireccion(request.getDireccion());
        u.setCodigoPostal(request.getCodigoPostal());
        u.setRol(request.getRol());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            u.setPasswordHash(request.getPassword());
        }

        return toDTO(usuarioRepository.save(u));
    }

    @Override
    @Transactional
    public void borrarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    // ============================================================
    // ================        MÉTODO PRIVADO       ================
    // ============================================================

    private UsuarioDTO toDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getIdUsuario());
        dto.setNombre(u.getNombre());
        dto.setApellidos(u.getApellidos());
        dto.setEmail(u.getEmail());
        dto.setRol(u.getRol());
        dto.setDireccion(u.getDireccion());
        dto.setCodigoPostal(u.getCodigoPostal());
        dto.setFechaAlta(u.getFechaAlta());
        return dto;
    }
}

package com.ritualcoffee.crm.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ritualcoffee.crm.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    // Clave secreta para firmar el token.
    // Puedes moverla a application.properties si quieres.
    private final SecretKey secretKey;

    // Duración del token (por ejemplo, 24 horas)
    private final long expirationMs = 24 * 60 * 60 * 1000; // 1 día

    public JwtUtil(
            @Value("${app.jwt.secret:MiClaveSuperSecretaParaJWT1234567890}") String secret) {
        // Debe tener tamaño mínimo para HS256, por eso usamos getBytes()
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // ====================== GENERAR TOKEN ======================

    public String generarToken(Usuario usuario) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(usuario.getEmail())          // identificador principal
                .claim("rol", usuario.getRol().name())   // metemos el rol en el token
                .setIssuedAt(ahora)                      // fecha de emisión
                .setExpiration(expiracion)               // fecha de expiración
                .signWith(secretKey, SignatureAlgorithm.HS256) // firma
                .compact();
    }

    // ====================== LEER TOKEN ======================

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Email del usuario (subject)
    public String obtenerEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Rol (por si lo quieres usar)
    public String obtenerRol(String token) {
        Object rol = getClaims(token).get("rol");
        return rol != null ? rol.toString() : null;
    }

    // ¿Token caducado?
    public boolean estaTokenExpirado(String token) {
        Date expiracion = getClaims(token).getExpiration();
        return expiracion.before(new Date());
    }

    // ¿Token válido para este email concreto?
    public boolean esTokenValido(String token, String emailEsperado) {
        String email = obtenerEmail(token);
        return email.equals(emailEsperado) && !estaTokenExpirado(token);
    }
}

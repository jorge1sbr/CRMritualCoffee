package com.ritualcoffee.crm.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ritualcoffee.crm.repository.UsuarioRepository;
import com.ritualcoffee.crm.entity.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String email = null;
        String token = null;

  
        // 1. Extraer token del header
     
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            email = jwtUtil.obtenerEmail(token);
        }

     
        // 2. Validar token y autenticar

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            Usuario u = usuarioRepository.findByEmail(email).orElse(null);

            if (u != null && jwtUtil.esTokenValido(token, email)) {

                UserDetails userDetails = User.withUsername(u.getEmail())
                        .password(u.getPasswordHash())
                        .roles(u.getRol().name())
                        .build();

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Esto mete al usuario autenticado en Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // ==============================
        // 3. Continuar con la cadena
        // ==============================
        filterChain.doFilter(request, response);
    }
}

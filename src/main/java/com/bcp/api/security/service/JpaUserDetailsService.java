package com.bcp.api.security.service;

import com.bcp.api.security.model.Usuario;
import com.bcp.api.security.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * Servicio para cargar los detalles de usuario para la autenticación
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema");
                    return new UsernameNotFoundException("Username: " + username + " no existe en el sistema");
                });

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEnabled(),
                true,
                true,
                true,
                getDefaultAuthorities()
        );
    }

    /**
     * Retorna una autoridad por defecto para todos los usuarios autenticados
     *
     * @return Colección con una autoridad básica
     */
    private Collection<? extends GrantedAuthority> getDefaultAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
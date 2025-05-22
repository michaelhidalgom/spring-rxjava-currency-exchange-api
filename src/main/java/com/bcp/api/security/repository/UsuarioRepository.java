package com.bcp.api.security.repository;

import com.bcp.api.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para acceder a los datos de usuarios
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario
     *
     * @param username Nombre de usuario
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    Optional<Usuario> findByUsername(String username);

    /**
     * Busca un usuario por su nombre de usuario o email
     *
     * @param username Nombre de usuario
     * @param email Email
     * @return Optional con el usuario encontrado o vacío si no existe
     */
    Optional<Usuario> findByUsernameOrEmail(String username, String email);

    /**
     * Verifica si existe un usuario con el nombre de usuario dado
     *
     * @param username Nombre de usuario
     * @return true si existe, false en caso contrario
     */
    Boolean existsByUsername(String username);

    /**
     * Verifica si existe un usuario con el email dado
     *
     * @param email Email
     * @return true si existe, false en caso contrario
     */
    Boolean existsByEmail(String email);
}
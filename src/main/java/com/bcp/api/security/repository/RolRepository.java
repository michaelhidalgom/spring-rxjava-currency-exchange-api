package com.bcp.api.security.repository;

import com.bcp.api.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para acceder a los datos de roles
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
    /**
     * Busca un rol por su nombre (authority)
     * 
     * @param authority Nombre del rol
     * @return Optional con el rol encontrado o vacío si no existe
     */
    Optional<Rol> findByAuthority(String authority);
}

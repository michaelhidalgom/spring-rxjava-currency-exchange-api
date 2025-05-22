package com.bcp.api.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entidad que representa un usuario en el sistema
 */
@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;
}
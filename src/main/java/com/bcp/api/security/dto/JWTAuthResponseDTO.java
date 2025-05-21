package com.bcp.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO para la respuesta JWT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponseDTO {
    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";
    
    public JWTAuthResponseDTO(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }
}

package com.bcp.api.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Componente para generar y validar tokens JWT
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret:JWTSecretKey}")
    private String jwtSecret;

    @Value("${app.jwt.expiration:86400000}")
    private int jwtExpirationInMs;

    /**
     * Genera un token JWT a partir de la autenticación
     *
     * @param authentication Información de autenticación
     * @return Token JWT generado
     */
    public String generarToken(Authentication authentication) {
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(fechaActual)
                .setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Obtiene el nombre de usuario del token JWT
     *
     * @param token Token JWT
     * @return Nombre de usuario
     */
    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Valida un token JWT
     *
     * @param token Token a validar
     * @return true si el token es válido, false si no
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token JWT no válido");
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado");
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT no compatible");
        } catch (IllegalArgumentException e) {
            logger.error("La cadena claims JWT está vacía");
        } catch (SignatureException e) {
            logger.error("La firma del token JWT no es válida");
        }
        return false;
    }
}

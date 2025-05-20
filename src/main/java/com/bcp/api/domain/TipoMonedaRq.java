package com.bcp.api.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud para crear un nuevo tipo de cambio
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoMonedaRq {
	// Código de la moneda de origen (ISO 4217), ejemplo: USD
	private String monedaOrigen;

	// Código de la moneda de destino (ISO 4217), ejemplo: PEN
	private String monedaDestino;

	// Factor de equivalencia entre las monedas, ejemplo: 3.70
	private BigDecimal equivalencia;
}
package com.bcp.api.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud para actualizar un tipo de cambio existente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoMonedaUpdRq {
	// ID del tipo de cambio a actualizar
	private Integer idTipoCambio;

	// Código de la moneda de origen (ISO 4217), ejemplo: USD
	private String monedaOrigen;

	// Código de la moneda de destino (ISO 4217), ejemplo: PEN
	private String monedaDestino;

	// Factor de equivalencia entre las monedas, ejemplo: 3.70
	private BigDecimal equivalencia;
}
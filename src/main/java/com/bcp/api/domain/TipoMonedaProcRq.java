package com.bcp.api.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa una solicitud de conversión de moneda
 * Contiene los datos necesarios para procesar una operación de cambio
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoMonedaProcRq {
	// Código de la moneda de origen (ISO 4217), ejemplo: USD
	private String monedaOrigen;

	// Código de la moneda de destino (ISO 4217), ejemplo: PEN
	private String monedaDestino;

	// Monto a convertir, ejemplo: 100.00
	private BigDecimal monto;
}
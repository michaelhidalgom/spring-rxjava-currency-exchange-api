package com.bcp.api.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el resultado de una conversión de moneda
 * Contiene tanto los datos de la solicitud como el resultado de la conversión
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TipoMonedaProcRs {

	// Código de la moneda de origen (ISO 4217), ejemplo: USD
	private String monedaOrigen;

	// Código de la moneda de destino (ISO 4217), ejemplo: PEN
	private String monedaDestino;

	// Monto original a convertir
	private BigDecimal monto;

	// Monto convertido según el tipo de cambio
	private BigDecimal montoCambio;
}
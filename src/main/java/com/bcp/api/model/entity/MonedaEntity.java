package com.bcp.api.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un tipo de cambio entre dos monedas
 * Mapea la tabla 'monedas' en la base de datos
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "monedas")
public class MonedaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipocambio")
	private Integer idTipoCambio;

	@Column(name = "monedaorigen", nullable = false, length = 3)
	private String monedaOrigen;

	@Column(name = "monedadestino", nullable = false, length = 3)
	private String monedaDestino;

	@Column(name = "equivalencia", nullable = false, precision = 10, scale = 3)
	private BigDecimal equivalencia;
}
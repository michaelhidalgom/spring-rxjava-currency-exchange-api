package com.bcp.api.repository;

import com.bcp.api.model.entity.MonedaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a los datos de tipos de cambio
 * Extiende JpaRepository para operaciones CRUD básicas
 */
@Repository
public interface MonedasRepository extends JpaRepository<MonedaEntity, Integer> {

	/**
	 * Busca un tipo de cambio por moneda de origen y moneda de destino
	 *
	 * @param monedaOrigen Código de la moneda de origen
	 * @param monedaDestino Código de la moneda de destino
	 * @return El tipo de cambio encontrado o null si no existe
	 */
	MonedaEntity findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
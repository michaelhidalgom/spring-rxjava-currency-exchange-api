package com.bcp.api.service;

import java.util.List;

import com.bcp.api.domain.TipoMonedaProcRq;
import com.bcp.api.domain.TipoMonedaProcRs;
import com.bcp.api.domain.TipoMonedaRq;
import com.bcp.api.domain.TipoMonedaUpdRq;
import com.bcp.api.model.entity.MonedaEntity;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Interfaz que define los servicios para la gestión de tipos de cambio
 */
public interface TipoCambioService {

	/**
	 * Obtiene todos los tipos de cambio
	 *
	 * @return Lista de todos los tipos de cambio
	 */
	List<MonedaEntity> buscarTodos();

	/**
	 * Obtiene todos los tipos de cambio como un flujo Observable
	 *
	 * @return Observable con todos los tipos de cambio
	 */
	Observable<MonedaEntity> listarTodosTiposCambio();

	/**
	 * Guarda un nuevo tipo de cambio
	 *
	 * @param tipoCambio Datos del tipo de cambio a guardar
	 * @return Single con el tipo de cambio guardado
	 */
	Single<MonedaEntity> guardarTipoCambio(TipoMonedaRq tipoCambio);

	/**
	 * Actualiza un tipo de cambio existente
	 *
	 * @param tipoCambio Datos del tipo de cambio a actualizar
	 * @return Single con el tipo de cambio actualizado
	 */
	Single<MonedaEntity> actualizarTipoCambio(TipoMonedaUpdRq tipoCambio);

	/**
	 * Procesa una conversión de moneda
	 *
	 * @param solicitudConversion Datos de la conversión a procesar
	 * @return Observable con el resultado de la conversión
	 */
	Observable<TipoMonedaProcRs> procesarConversion(TipoMonedaProcRq solicitudConversion);
}
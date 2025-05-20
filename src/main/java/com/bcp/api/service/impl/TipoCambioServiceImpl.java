package com.bcp.api.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcp.api.domain.TipoMonedaProcRq;
import com.bcp.api.domain.TipoMonedaProcRs;
import com.bcp.api.domain.TipoMonedaRq;
import com.bcp.api.domain.TipoMonedaUpdRq;
import com.bcp.api.model.entity.MonedaEntity;
import com.bcp.api.repository.MonedasRepository;
import com.bcp.api.service.TipoCambioService;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Implementación de los servicios para la gestión de tipos de cambio
 * Esta clase implementa la lógica de negocio para las operaciones con tipos de cambio
 */
@Service
public class TipoCambioServiceImpl implements TipoCambioService {

	private final MonedasRepository monedasRepository;

	@Autowired
	public TipoCambioServiceImpl(MonedasRepository monedasRepository) {
		this.monedasRepository = monedasRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MonedaEntity> buscarTodos() {
		return monedasRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Observable<MonedaEntity> listarTodosTiposCambio() {
		return Observable.fromIterable(monedasRepository.findAll());
	}

	@Override
	@Transactional
	public Single<MonedaEntity> guardarTipoCambio(TipoMonedaRq tipoCambio) {
		return Single.just(tipoCambio)
				.map(mapAEntidadNueva)
				.map(entidad -> monedasRepository.save(entidad));
	}

	@Override
	@Transactional
	public Single<MonedaEntity> actualizarTipoCambio(TipoMonedaUpdRq tipoCambio) {
		return Single.just(tipoCambio)
				.map(mapAEntidadExistente)
				.map(entidad -> monedasRepository.save(entidad));
	}

	@Override
	@Transactional(readOnly = true)
	public Observable<TipoMonedaProcRs> procesarConversion(TipoMonedaProcRq solicitud) {

		return Observable.just(solicitud)
				.flatMap(req ->
						Observable.just(monedasRepository.findByMonedaOrigenAndMonedaDestino(
										req.getMonedaOrigen(),
										req.getMonedaDestino()
								))
								.filter(tipoCambio -> tipoCambio != null)
								.switchIfEmpty(Observable.error(
										new RuntimeException("No existe tipo de cambio para las monedas especificadas")
								))
								.flatMap(tipoCambio -> aplicarConversion.apply(req.getMonto(), tipoCambio))
				);
	}

	/**
	 * Función para mapear solicitud a entidad nueva
	 * Transforma un objeto TipoMonedaRq en un objeto MonedaEntity
	 */
	private final Function<TipoMonedaRq, MonedaEntity> mapAEntidadNueva = tipoCambio ->
			MonedaEntity.builder()
					.monedaOrigen(tipoCambio.getMonedaOrigen())
					.monedaDestino(tipoCambio.getMonedaDestino())
					.equivalencia(tipoCambio.getEquivalencia())
					.build();

	/**
	 * Función para mapear solicitud de actualización a entidad existente
	 * Transforma un objeto TipoMonedaUpdRq en un objeto MonedaEntity
	 */
	private final Function<TipoMonedaUpdRq, MonedaEntity> mapAEntidadExistente = tipoCambio ->
			MonedaEntity.builder()
					.idTipoCambio(tipoCambio.getIdTipoCambio())
					.monedaOrigen(tipoCambio.getMonedaOrigen())
					.monedaDestino(tipoCambio.getMonedaDestino())
					.equivalencia(tipoCambio.getEquivalencia())
					.build();

	/**
	 * Función para aplicar la conversión entre monedas
	 * Calcula el monto convertido a partir del monto original y el tipo de cambio
	 */
	private final BiFunction<BigDecimal, MonedaEntity, Observable<TipoMonedaProcRs>> aplicarConversion =
			(monto, tipoCambio) -> Observable.just(
					TipoMonedaProcRs.builder()
							.monedaOrigen(tipoCambio.getMonedaOrigen())
							.monedaDestino(tipoCambio.getMonedaDestino())
							.monto(monto)
							.montoCambio(monto.multiply(tipoCambio.getEquivalencia()))
							.build()
			);
}
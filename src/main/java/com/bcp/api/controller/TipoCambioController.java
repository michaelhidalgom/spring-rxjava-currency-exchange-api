package com.bcp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.api.domain.TipoMonedaProcRq;
import com.bcp.api.domain.TipoMonedaProcRs;
import com.bcp.api.domain.TipoMonedaRq;
import com.bcp.api.domain.TipoMonedaUpdRq;
import com.bcp.api.model.entity.MonedaEntity;
import com.bcp.api.service.TipoCambioService;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Controlador REST para la gestión de tipos de cambio
 * Proporciona endpoints para listar, guardar, actualizar y procesar tipos de cambio
 */
@RestController
@RequestMapping("/api/tipo-cambio")
public class TipoCambioController {

    private final TipoCambioService tipoCambioService;

    /**
     * Constructor con inyección de dependencias
     *
     * @param tipoCambioService servicio para gestionar los tipos de cambio
     */
    @Autowired
    public TipoCambioController(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    /**
     * Endpoint para listar todos los tipos de cambio
     *
     * @return Observable con los tipos de cambio ordenados por moneda de origen
     */
    @GetMapping("/listar")
    public Observable<MonedaEntity> listarTiposCambio() {
        return tipoCambioService.listarTodosTiposCambio()
                .sorted((m1, m2) -> m1.getMonedaOrigen().compareTo(m2.getMonedaOrigen()));
    }

    /**
     * Endpoint para guardar un nuevo tipo de cambio
     *
     * @param tipoCambio datos del tipo de cambio a guardar
     * @return Single con el tipo de cambio guardado
     */
    @PostMapping("/guardar")
    public Single<MonedaEntity> guardarTipoCambio(@RequestBody TipoMonedaRq tipoCambio) {
        return tipoCambioService.guardarTipoCambio(tipoCambio);
    }

    /**
     * Endpoint para actualizar un tipo de cambio existente
     *
     * @param tipoCambio datos del tipo de cambio a actualizar
     * @return Single con el tipo de cambio actualizado
     */
    @PutMapping("/actualizar")
    public Single<MonedaEntity> actualizarTipoCambio(@RequestBody TipoMonedaUpdRq tipoCambio) {
        return tipoCambioService.actualizarTipoCambio(tipoCambio);
    }

    /**
     * Endpoint para procesar una conversión entre monedas
     *
     * @param solicitudConversion datos para la conversión a procesar
     * @return Observable con el resultado de la conversión
     */
    @PostMapping(value = "/procesar")
    public Observable<TipoMonedaProcRs> procesarConversion(@RequestBody TipoMonedaProcRq solicitudConversion) {
        return tipoCambioService.procesarConversion(solicitudConversion);
    }
}
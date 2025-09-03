package co.com.pragmasolicitudes.usecase.solicitud;

import co.com.pragmasolicitudes.model.solicitud.Solicitud;
import co.com.pragmasolicitudes.model.solicitud.gateways.SolicitudRepository;
import co.com.pragmasolicitudes.model.tipoprestamo.gateways.TipoPrestamoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class SolicitudUseCase {

    private final SolicitudRepository solicitudRepository;
    private final TipoPrestamoRepository tipoPrestamoRepository;

    public Mono<Solicitud> guardarSolicitud(Solicitud solicitud){
        //validaciones de negocio

        // validar que el tipo de prestamo sea un tipo existente

        //registrar autamaticamente con un estado inicial de pendiente por revision
        // 1. Validar que el tipo de préstamo sea existente.
        return tipoPrestamoRepository.consultarPorId(solicitud.getIdTipoPrestamo())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El tipo de prestamo no existe")))
                .flatMap(tipoPrestamo -> {
                    // 2. Si el tipo de prestamo existe, continúa con el guardado de la solicitud.
                    solicitud.setIdEstado(1L); //pendiente mapear esto a un enum puede ser
                    return solicitudRepository.guardar(solicitud);
                });
    }

}

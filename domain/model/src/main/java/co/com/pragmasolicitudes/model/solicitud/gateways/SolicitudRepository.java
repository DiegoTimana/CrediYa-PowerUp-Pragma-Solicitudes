package co.com.pragmasolicitudes.model.solicitud.gateways;

import co.com.pragmasolicitudes.model.solicitud.Solicitud;
import reactor.core.publisher.Mono;

public interface SolicitudRepository {

    Mono<Solicitud> guardar(Solicitud solicitud);

}

package co.com.pragmasolicitudes.model.tipoprestamo.gateways;

import co.com.pragmasolicitudes.model.solicitud.Solicitud;
import co.com.pragmasolicitudes.model.tipoprestamo.TipoPrestamo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoPrestamoRepository {

    Mono<TipoPrestamo> consultarPorId(Long idTipoPrestamo);

    Flux<TipoPrestamo> consultarTodos();

}

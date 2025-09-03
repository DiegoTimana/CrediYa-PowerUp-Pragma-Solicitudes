package co.com.pragmasolicitudes.model.estados.gateways;

import co.com.pragmasolicitudes.model.estados.Estado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstadoRepository {

    Mono<Estado> consultarPorId(Long idEstado);

    Flux<Estado> consultarTodos();

}

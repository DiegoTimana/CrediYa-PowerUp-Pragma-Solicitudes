package co.com.pragmasolicitudes.r2dbc;

import co.com.pragmasolicitudes.model.solicitud.Solicitud;
import co.com.pragmasolicitudes.model.solicitud.gateways.SolicitudRepository;
import co.com.pragmasolicitudes.model.tipoprestamo.TipoPrestamo;
import co.com.pragmasolicitudes.model.tipoprestamo.gateways.TipoPrestamoRepository;
import co.com.pragmasolicitudes.r2dbc.entity.SolicitudEntity;
import co.com.pragmasolicitudes.r2dbc.entity.TipoPrestamoEntity;
import co.com.pragmasolicitudes.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TipoPrestamoReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        TipoPrestamo/* change for domain model */,
        TipoPrestamoEntity/* change for adapter model */,
        Long,
        TipoPrestamoReactiveRepository>  implements TipoPrestamoRepository {
    public TipoPrestamoReactiveRepositoryAdapter(TipoPrestamoReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, TipoPrestamo.class/* change for domain model */));
    }

    @Override
    public Mono<TipoPrestamo> consultarPorId(Long idTipoPrestamo) {
        return super.findById(idTipoPrestamo);
    }

    @Override
    public Flux<TipoPrestamo> consultarTodos() {
        return super.findAll();
    }
}

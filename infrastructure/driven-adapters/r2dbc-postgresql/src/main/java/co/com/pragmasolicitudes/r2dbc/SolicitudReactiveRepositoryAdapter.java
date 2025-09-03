package co.com.pragmasolicitudes.r2dbc;

import co.com.pragmasolicitudes.model.solicitud.Solicitud;
import co.com.pragmasolicitudes.model.solicitud.gateways.SolicitudRepository;
import co.com.pragmasolicitudes.r2dbc.entity.SolicitudEntity;
import co.com.pragmasolicitudes.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
public class SolicitudReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Solicitud/* change for domain model */,
        SolicitudEntity/* change for adapter model */,
        Long,
        SolicitudReactiveRepository>  implements SolicitudRepository {
    public SolicitudReactiveRepositoryAdapter(SolicitudReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Solicitud.class/* change for domain model */));
    }

    @Override
    @Transactional
    public Mono<Solicitud> guardar(Solicitud solicitud) {
        return super.save(solicitud);
    }
}

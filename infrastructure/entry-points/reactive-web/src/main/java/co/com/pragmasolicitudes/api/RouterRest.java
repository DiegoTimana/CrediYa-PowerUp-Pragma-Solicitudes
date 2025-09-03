package co.com.pragmasolicitudes.api;

import co.com.pragmasolicitudes.api.config.OpenApiSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

    private final Handler handler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route()
                .POST("/api/v1/solicitud", handler::listenRegistrarSolicitud, OpenApiSwagger::registrarUsuario)
                .build();
    }
}

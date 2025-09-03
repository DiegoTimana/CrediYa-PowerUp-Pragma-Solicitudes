package co.com.pragmasolicitudes.api.exceptionHandler;

import co.com.pragmasolicitudes.api.dto.ErrorResponseDTO;

import co.com.pragmasolicitudes.log.CrediYaLog;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;

@Component
//@Order(-2) // prioridad antes del handler por defecto de Spring
public class GlobalErrorHandler extends AbstractErrorWebExceptionHandler {

    private final CrediYaLog crediYaLog;

    public GlobalErrorHandler(ApplicationContext applicationContext,
                              ServerCodecConfigurer codecConfigurer,
                              CrediYaLog crediYaLog) {
        super(new DefaultErrorAttributes(), new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(codecConfigurer.getWriters());
        super.setMessageReaders(codecConfigurer.getReaders());
        this.crediYaLog = crediYaLog;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Throwable error = getError(request);

        // Puedes mapear diferentes excepciones a diferentes códigos HTTP
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String mensaje = "Error interno en el servidor";

        if (error instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            mensaje = error.getMessage();
        } else if (error instanceof RuntimeException) {
            status = HttpStatus.CONFLICT;
            mensaje = error.getMessage();
        }

        ErrorResponseDTO response = new ErrorResponseDTO(
                status.value(),
                mensaje,
                request.path(),
                LocalDateTime.now().toString()
        );

        crediYaLog.error("Error manejado en GlobalErrorHandler: {} ", response.toString());


        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response);
    }
}

package co.com.pragmasolicitudes.api;

import co.com.pragmasolicitudes.api.dto.CrearSolicitudDTO;
import co.com.pragmasolicitudes.api.dto.SolicitudDTO;
import co.com.pragmasolicitudes.log.CrediYaLog;
import co.com.pragmasolicitudes.model.solicitud.Solicitud;
import co.com.pragmasolicitudes.usecase.solicitud.SolicitudUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;
    private final SolicitudUseCase solicitudUseCase;
    private final ObjectMapper objectMapper;
    private final CrediYaLog crediYaLog;

    public Mono<ServerResponse> listenRegistrarSolicitud(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CrearSolicitudDTO.class)
                .map(solicitudDTO -> objectMapper.convertValue(solicitudDTO, Solicitud.class))
                .doOnNext(solicitud -> crediYaLog.info("Iniciando registro de una solicitud de prestamo, cédula del cliente: {}",
                        solicitud.getDocumentoIdentidad()))
                .flatMap(solicitudUseCase::guardarSolicitud)
                .doOnSuccess(solicitudGuardada -> crediYaLog.info("Solicitud registrada exitosamente con id: {}", solicitudGuardada.getIdSolicitud()))
                .flatMap(solicitudGuardada ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(objectMapper.convertValue(solicitudGuardada, SolicitudDTO.class))
                );
    }
}

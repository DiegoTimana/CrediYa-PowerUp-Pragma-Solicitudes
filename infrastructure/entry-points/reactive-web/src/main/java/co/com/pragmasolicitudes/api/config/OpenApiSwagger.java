package co.com.pragmasolicitudes.api.config;

import co.com.pragmasolicitudes.api.dto.CrearSolicitudDTO;
import co.com.pragmasolicitudes.api.dto.ErrorResponseDTO;
import co.com.pragmasolicitudes.api.dto.SolicitudDTO;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;

@UtilityClass
public class OpenApiSwagger {

    private final String SUCCESS = "Success";
    private final String SUCCESS_CODE = String.valueOf(HttpStatus.OK.value());
    private final String CREATED_CODE = String.valueOf(HttpStatus.CREATED.value());
    private final String BAD_REQUEST = HttpStatus.BAD_REQUEST.getReasonPhrase();
    private final String BAD_REQUEST_CODE = String.valueOf(HttpStatus.BAD_REQUEST.value());
    private final String INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    private final String INTERNAL_ERROR_CODE = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    private final String CONFLICT_ERROR_CODE = String.valueOf(HttpStatus.CONFLICT.value());

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de solicitudes - CrediYa")
                        .version("1.0.0")
                        .description("Microservicio para gestión de solicitudes de crédito en CrediYa con WebFlux"));
    }

    public Builder registrarSolicitud(Builder builder) {
        return builder
                .operationId("registrarSolicitud")
                .description("Crea una nueva solicitud de crédito en CrediYa")
                .tag("Solicitud")
                .requestBody(requestBodyBuilder()
                        .required(true)
                        .content(contentBuilder().mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(CrearSolicitudDTO.class))))
                .response(responseBuilder().responseCode(CREATED_CODE).description("Solicitud registrada")
                        .content(contentBuilder().mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(SolicitudDTO.class))))
                .response(responseBuilder().responseCode(CONFLICT_ERROR_CODE).description(BAD_REQUEST)
                        .content(contentBuilder().mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(ErrorResponseDTO.class))));
    }


}

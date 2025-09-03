package co.com.pragmasolicitudes.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorResponseDTO(
        @Schema(description = "Estado HTTP de la respuesta de error", example = "400")
        int status,
        @Schema(description = "Mensaje de la respuesta de error", example = "El tipo de prestamo no existe")
        String mensaje,
        @Schema(description = "Ruta del endpoint que retorno la respuesta de error", example = "/api/v1/solicitud")
        String path,
        @Schema(description = "Marca de tiempo de cuando se originó la respuesta de error", example = "2025-08-30T16:01:08.752384400")
        String timestamp
) {
}

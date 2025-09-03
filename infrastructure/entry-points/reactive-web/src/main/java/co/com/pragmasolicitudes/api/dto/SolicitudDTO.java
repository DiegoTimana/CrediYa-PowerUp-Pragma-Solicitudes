package co.com.pragmasolicitudes.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SolicitudDTO(
        @Schema(description = "Identificador de la solicitud", example = "3")
        Long idSolicitud,
        @Schema(description = "Monto del crédito solicitado en COP", example = "50000000")
        BigDecimal monto,
        @Schema(description = "Plazo para pagar el crédito", example = "2026-01-01")
        LocalDate plazo,
        @Schema(description = "Correo electrónico válido", example = "diego@mail.com")
        String email,
        @Schema(description = "Número del documento de identidad del solicitante", example = "100499102")
        int documentoIdentidad,
        @Schema(description = "Identificador del tipo de préstamo a solicitar", example = "1")
        Long idTipoPrestamo,
        @Schema(description = "Nombres del usuario", example = "Diego")
        Long idEstado
) {

}

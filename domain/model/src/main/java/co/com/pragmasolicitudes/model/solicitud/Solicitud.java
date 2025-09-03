package co.com.pragmasolicitudes.model.solicitud;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Solicitud {

    private Long idSolicitud;
    private BigDecimal monto;
    private LocalDate plazo;
    private String email;
    private int documentoIdentidad;
    private Long idEstado;
    private Long idTipoPrestamo;

}

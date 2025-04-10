package dsw.sigconbackend.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRequest {
    private Long idPersona;    
    private String apellidoPaterno;    
    private String apellidoMaterno;
    private String nombres;    
    private Date fechaNacimiento;
    private Integer idTipoDocumento;
    private String nDocumento;
    private String direccion; 
    private String idUbigeo; 
}

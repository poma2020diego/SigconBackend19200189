package dsw.sigconbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//permitira manejar codigo limpio: creara los getter, setter, constructores
@Entity//permitira realizar las operaciones CRUD 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tipo_documento")
public class TipoDocumento {
    @Id
    @Column(name="id_tipo_documento")
    private Integer idTipoDocumento;
    private String descripcion;
}

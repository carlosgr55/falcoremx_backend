package falcoremx.com.FalcoreMX.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "direcciones_empresas")
@Data
@ToString(exclude = {"empresaEntity", "direccionEntity"})
public class DireccionEmpresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "empresa")
    private Integer empresa;

    @Column(name = "direccion")
    private Integer direccion;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "empresa", insertable = false, updatable = false)
    @JsonIgnore
    private Empresa empresaEntity;

    @ManyToOne
    @JoinColumn(name = "direccion", insertable = false, updatable = false)
    @JsonIgnore
    private Direccion direccionEntity;
}
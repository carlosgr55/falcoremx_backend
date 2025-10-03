package falcoremx.com.FalcoreMX.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "direccion")
@Data
@ToString(exclude = {"direccionEmpresas"})
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "linea2")
    private String linea2;

    // Relaciones
    @OneToMany(mappedBy = "direccionEntity")
    private List<DireccionEmpresa> direccionEmpresas;
}
package falcoremx.com.FalcoreMX.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data
@ToString(exclude = {"empresaEntity", "userEntity"})
public class Product {
    
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "sku", length = 50, nullable = false)
    private String sku;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "empresa")
    private Integer empresa;

    @Column(name = "user")
    private String user;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "descripcionadicional", columnDefinition = "TEXT")
    private String descripcionAdicional;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "imagen", length = 500)
    private String imagen;

    @Column(name = "leadtime", length = 50)
    private String leadtime;

    @Column(name = "disponible")
    private Boolean disponible;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "empresa", insertable = false, updatable = false)
    private Empresa empresaEntity;

    @ManyToOne
    @JoinColumn(name = "user", insertable = false, updatable = false)
    private User userEntity;
}
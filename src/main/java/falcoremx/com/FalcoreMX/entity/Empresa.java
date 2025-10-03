package falcoremx.com.FalcoreMX.entity;

import jakarta.persistence.*;
import falcoremx.com.FalcoreMX.entity.User;
import falcoremx.com.FalcoreMX.entity.Product;
import falcoremx.com.FalcoreMX.entity.DireccionEmpresa;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "empresas")
@Data
@ToString(exclude = {"users", "products", "direccionEmpresas"})
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rfc", length = 15)
    private String rfc;

    @Column(name = "razon_social")
    private String razonSocial;

    // Relaciones
    @OneToMany(mappedBy = "empresa")
    private List<User> users;

    @OneToMany(mappedBy = "empresaEntity")
    private List<Product> products;

    @OneToMany(mappedBy = "empresaEntity")
    private List<DireccionEmpresa> direccionEmpresas;
}
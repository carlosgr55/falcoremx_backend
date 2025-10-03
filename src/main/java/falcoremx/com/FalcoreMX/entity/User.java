package falcoremx.com.FalcoreMX.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@ToString(exclude = {"empresa", "rolesEmpleados", "products"})
public class User {
    
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "id_empresa")
    private Integer idEmpresa;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_empresa", insertable = false, updatable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "userEntity")
    private List<RolEmpleado> rolesEmpleados;

    @OneToMany(mappedBy = "userEntity")
    private List<Product> products;
}

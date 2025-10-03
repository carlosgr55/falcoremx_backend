package falcoremx.com.FalcoreMX.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Entity
@Table(name = "rol")
@Data
@ToString(exclude = {"rolesEmpleados"})
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rol")
    private String rol;

    // Relaciones
    @OneToMany(mappedBy = "rolEntity")
    private List<RolEmpleado> rolesEmpleados;
}
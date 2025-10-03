package falcoremx.com.FalcoreMX.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore
    private List<RolEmpleado> rolesEmpleados;
}
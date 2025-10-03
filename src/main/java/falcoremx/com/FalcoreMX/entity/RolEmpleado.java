package falcoremx.com.FalcoreMX.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "roles_empleados")
@Data
@ToString(exclude = {"userEntity", "rolEntity"})
public class RolEmpleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user")
    private String user;

    @Column(name = "rol")
    private Integer rol;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "user", insertable = false, updatable = false)
    @JsonIgnore
    private User userEntity;

    @ManyToOne
    @JoinColumn(name = "rol", insertable = false, updatable = false)
    @JsonIgnore
    private Rol rolEntity;
}
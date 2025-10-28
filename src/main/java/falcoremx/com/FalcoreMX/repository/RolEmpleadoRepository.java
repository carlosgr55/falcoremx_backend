package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.RolEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface RolEmpleadoRepository extends JpaRepository<RolEmpleado, Integer> {

    @Query("SELECT r FROM RolEmpleado r WHERE r.user = ?1")
    List<RolEmpleado> findRolByEmpleado(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM RolEmpleado r WHERE r.user = ?1 AND r.rol = ?2")
    void deleteByUserAndRolId(String username, Integer rolId);

    List<RolEmpleado> findByUser(String user);

    void deleteByUserAndRol(String user, Integer rol);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roles_empleado(user, rol) VALUES (?1, ?2)", nativeQuery = true)
    void insertRolEmpleado(String username, Integer rolId);
}

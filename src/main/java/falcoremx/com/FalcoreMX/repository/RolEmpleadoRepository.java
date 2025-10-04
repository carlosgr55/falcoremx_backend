package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.RolEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RolEmpleadoRepository extends JpaRepository<RolEmpleado, Integer> {

@Query("SELECT r FROM RolEmpleado r WHERE r.user = ?1")
List<RolEmpleado> findRolByEmpleado(String username);


}

package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.DireccionEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DireccionEmpresaRepository extends JpaRepository<DireccionEmpresa, Integer> {

    @Query("SELECT d FROM DireccionEmpresa d WHERE d.empresa = ?1")
    List<DireccionEmpresa> findByEmpresaId(Integer idEmpresa);

}

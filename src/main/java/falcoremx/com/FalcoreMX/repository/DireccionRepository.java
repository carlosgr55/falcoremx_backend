package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
}

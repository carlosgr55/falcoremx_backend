package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserAndActivo(String user, Boolean activo);
    Optional<Cart> findByUserAndActivoTrue(String user);

}
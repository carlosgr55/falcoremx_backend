package falcoremx.com.FalcoreMX.repository;

import falcoremx.com.FalcoreMX.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductByEmpresa(Integer idEmpresa);
}

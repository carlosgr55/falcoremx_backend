package falcoremx.com.FalcoreMX.service;


import falcoremx.com.FalcoreMX.entity.Product;
import falcoremx.com.FalcoreMX.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductByEmpresa(Integer idEmpresa) {
        return productRepository.findProductByEmpresa(idEmpresa);
    }

    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }




    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}

package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<FakeStoreProductResponseDTO> getAllProducts() {
        return null;
    }

    @Override
    public FakeStoreProductResponseDTO getProduct(int productId) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public Product updateProduct(Product updatedProduct, int productId) {
        return null;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }
}

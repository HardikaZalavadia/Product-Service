package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<FakeStoreProductResponseDTO> getAllProducts();
    FakeStoreProductResponseDTO getProduct(int productId);
    Product createProduct(Product product);
    Product updateProduct(Product updatedProduct, int productId);
    boolean deleteProduct(int productId);
}

package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.dto.CreateProductRequestDTO;
import dev.hardika.EcomProductService.dto.ProductResponseDTO;
import dev.hardika.EcomProductService.dto.fakeStoreDTO.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProduct(UUID productId);
    ProductResponseDTO createProduct(CreateProductRequestDTO product);
    ProductResponseDTO updateProduct(CreateProductRequestDTO updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    ProductResponseDTO getProductByName(String name);
    List<ProductResponseDTO> getProduct(double minPrice, double maxPrice);
}

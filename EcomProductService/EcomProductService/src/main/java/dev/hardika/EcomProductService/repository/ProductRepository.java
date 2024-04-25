package dev.hardika.EcomProductService.repository;

import dev.hardika.EcomProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findProductByTitle(String name);
    Product findFirstProductByTitle(String name);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}

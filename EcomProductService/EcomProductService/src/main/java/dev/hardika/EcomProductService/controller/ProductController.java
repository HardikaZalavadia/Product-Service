package dev.hardika.EcomProductService.controller;

import dev.hardika.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.ProductIdInvalidException;
import dev.hardika.EcomProductService.exception.RandomException;
import dev.hardika.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<FakeStoreProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable("id") int id) {
        if(id < 1){
            throw new ProductIdInvalidException("Product Id is Invalid");
        }
        FakeStoreProductResponseDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/productexception")
    public ResponseEntity getProductException(){
        throw new RandomException("Product not found");
    }
}

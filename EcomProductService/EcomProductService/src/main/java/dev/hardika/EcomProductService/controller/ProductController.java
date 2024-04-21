package dev.hardika.EcomProductService.controller;

import dev.hardika.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.ProductIdInvalidException;
import dev.hardika.EcomProductService.exception.RandomException;
import dev.hardika.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody Product product){
        Product saveProduct = productService.createProduct(product);
        return ResponseEntity.ok(saveProduct);
    }

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

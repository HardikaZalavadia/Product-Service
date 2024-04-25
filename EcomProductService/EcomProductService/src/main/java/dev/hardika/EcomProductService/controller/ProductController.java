package dev.hardika.EcomProductService.controller;

import dev.hardika.EcomProductService.dto.CreateProductRequestDTO;
import dev.hardika.EcomProductService.dto.ProductResponseDTO;
import dev.hardika.EcomProductService.dto.fakeStoreDTO.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.exception.ProductIdInvalidException;
import dev.hardika.EcomProductService.exception.RandomException;
import dev.hardika.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO productRequestDTO){
        ProductResponseDTO saveProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(saveProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") UUID id) {
        if(id == null){
            throw new ProductIdInvalidException("Product Id is Invalid");
        }
        ProductResponseDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") UUID id, @RequestBody CreateProductRequestDTO requestDTO){

        return ResponseEntity.ok(productService.updateProduct(requestDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") UUID id){

        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductResponseDTO> getProductByName(@PathVariable("productName") String productName){
        return ResponseEntity.ok(productService.getProductByName(productName));
    }
    @GetMapping("/{min}/{max}")
    public ResponseEntity<List<ProductResponseDTO>> getProduct(@PathVariable("min") double minPrice, @PathVariable("max") double maxPrice){
        return ResponseEntity.ok(productService.getProduct(minPrice,maxPrice));
    }
    /*@GetMapping("/productexception")
    public ResponseEntity getProductException(){
        throw new RandomException("Product not found");
    }*/
}

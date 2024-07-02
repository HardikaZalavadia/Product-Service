package dev.hardika.EcomProductService.controller;

import dev.hardika.EcomProductService.client.userAuthClient.dto.SessionStatus;
import dev.hardika.EcomProductService.client.userAuthClient.UserAuthServiceClient;
import dev.hardika.EcomProductService.client.userAuthClient.dto.Role;
import dev.hardika.EcomProductService.client.userAuthClient.dto.ValidateResponseDto;
import dev.hardika.EcomProductService.dto.CreateProductRequestDTO;
import dev.hardika.EcomProductService.dto.ProductResponseDTO;


import dev.hardika.EcomProductService.client.orderServiceClient.dtoForOrder.OrderDto;
import dev.hardika.EcomProductService.exception.ProductIdInvalidException;
import dev.hardika.EcomProductService.service.ProductService;
import dev.hardika.EcomProductService.client.orderServiceClient.OrderServiceClient;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    //@Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    //@Autowired
    private UserAuthServiceClient userAuthService;

    @Autowired
    private OrderServiceClient orderService;

    private ProductController(ProductService productService, UserAuthServiceClient userAuthService, OrderServiceClient orderService) {
        this.productService = productService;
        this.userAuthService = userAuthService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO productRequestDTO){
        ProductResponseDTO saveProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(saveProduct);
    }

    @Cacheable(value = "allProducts")
    @GetMapping("allproducts")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    // Make only Admin can access all the products
    @Cacheable(value = "product")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@Nullable @RequestHeader("Auth_Token") String authToken,
                                                                   @Nullable @RequestHeader("User_Id") Long userId){

        //@Nullable gives go-ahead even if parameter is null
        //check if token exists
        if(userId == null || authToken == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ValidateResponseDto responseDto = userAuthService.validate(authToken,userId);
        if(!responseDto.getSessionStatus().equals(SessionStatus.ACTIVE)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean isUserAdmin = false;

        for(Role role : responseDto.getUserResponseDTO().getRole()){
             if(role.getRoleName().equalsIgnoreCase("admin")){
                 isUserAdmin = true;
             }
        }

        if(!isUserAdmin){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<ProductResponseDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Cacheable(value = "product", key = "#id")
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

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        // Here, you can add logic to validate the product ID and other business logic

        // For simplicity, let's assume the order request contains all necessary information
        orderService.sendOrder(orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order created and sent to Order Service");
    }

//    @GetMapping("/orderStatus/{orderId}")
//    public ResponseEntity<String> getOrderStatus(@PathVariable ("orderId") UUID orderId){
//        // create order status repository
//    }

    /*@GetMapping("/productexception")
    public ResponseEntity getProductException(){
        throw new RandomException("Product not found");
    }*/
}

package dev.hardika.EcomProductService.service.orderServiceClient;

import dev.hardika.EcomProductService.client.orderServiceClient.Order;
import dev.hardika.EcomProductService.client.orderServiceClient.OrderStatus;
import dev.hardika.EcomProductService.client.orderServiceClient.dtoForOrder.OrderDto;
import dev.hardika.EcomProductService.client.orderServiceClient.dtoForOrder.OrderResponseDto;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.ProductNotFoundException;
import dev.hardika.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceClient {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    private static final String ORDER_SERVICE_URL = "http://localhost:8082/orderService";

    public Order createOrder(OrderDto orderDto){
        List<UUID> productIds = orderDto.getProductIds();
        double totalPrice = 0.0;
        for(UUID productId : productIds){
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if(optionalProduct.isEmpty()){
                throw new ProductNotFoundException("Product not found");
            }
            Product product = optionalProduct.get();
            totalPrice += product.getPrice();
        }
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setProductIds(productIds);

        order.setUserId(orderDto.getUserId());
        order.setAddress(orderDto.getAddress());
        return order;

    }

    public void sendOrder(OrderDto orderDto){
        Order order = createOrder(orderDto);
        ResponseEntity<Order> response = restTemplate.postForEntity(ORDER_SERVICE_URL, order, Order.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            order.setOrderStatus(OrderStatus.CREATED);
            System.out.println("Order created successfully");
        } else {
            order.setOrderStatus(OrderStatus.FAIL);
            System.out.println("Failed to create order");
        }
    }
}

package dev.hardika.EcomProductService.client;

import dev.hardika.EcomProductService.dto.fakeStoreDTO.FakeStoreCartResponseDTO;
import dev.hardika.EcomProductService.dto.fakeStoreDTO.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakestore.api.base.url}")
    private String fakeStoreAPIBaseURL;
    @Value("${fakestore.api.product.path}")
    private String fakeStoreAPIProductPath;
    @Value("${fakestore.api.cart.for.user.path}")
    private String getFakeStoreAPICartForUser;
    @Value("fakestore.api.cart.path")
    private String fakeStoreAPICartPath;


    public List<FakeStoreProductResponseDTO> getAllProducts(){
        String fakeStoreGetAllProductURL = fakeStoreAPIBaseURL.concat(fakeStoreAPIProductPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseList =
                restTemplate.getForEntity(fakeStoreGetAllProductURL,FakeStoreProductResponseDTO[].class);
        return List.of(productResponseList.getBody());
    }

    public FakeStoreProductResponseDTO getProductById(int id){
        String fakeStoreProductURL = fakeStoreAPIBaseURL.concat(fakeStoreAPIProductPath).concat("/" + id);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.getForEntity(fakeStoreProductURL,FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public List<FakeStoreCartResponseDTO> getCartByUserId(int userId){
        // url - https://fakestoreapi.com/carts?userId=1
        String fakeStoreCartForUserURL =
                fakeStoreAPIBaseURL.concat(getFakeStoreAPICartForUser).concat(String.valueOf(userId));
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartResponseDTO[]> cartResponce =
                restTemplate.getForEntity(fakeStoreCartForUserURL,FakeStoreCartResponseDTO[].class);
        return List.of(cartResponce.getBody());
    }

    public FakeStoreCartResponseDTO getCartById(int id) {
//        https://fakestoreapi.com/carts/1
        String fakeStoreCartURL = fakeStoreAPIBaseURL.concat(fakeStoreAPICartPath).concat(String.valueOf(id));
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartResponseDTO> cart =
                restTemplate.getForEntity(fakeStoreCartURL,FakeStoreCartResponseDTO.class);
        return cart.getBody();

    }
}

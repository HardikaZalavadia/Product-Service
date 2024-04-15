package dev.hardika.EcomProductService.controller;

import dev.hardika.EcomProductService.client.FakeStoreClient;
import dev.hardika.EcomProductService.dto.FakeStoreCartResponseDTO;
import dev.hardika.EcomProductService.exception.CartNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private FakeStoreClient fakeStoreClient;

    @GetMapping("/cart/{userId}")
    public ResponseEntity getCartByUserId(@PathVariable("userId") int userId){
        List<FakeStoreCartResponseDTO> fakeStoreCartResponseDTO = fakeStoreClient.getCartByUserId(userId);
        if(fakeStoreCartResponseDTO == null){
            throw new CartNotFound("Cart Not Found of UserId : "+userId);
        }
        return ResponseEntity.ok(fakeStoreCartResponseDTO);
    }


}

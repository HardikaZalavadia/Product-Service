package dev.hardika.EcomProductService.dto.fakeStoreDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDTO {
    //All the attributes name should be match with its original attributes name when you call 3rd party service
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private FakeStoreRatingDTO rating; // In rating we need rating as well as count of it so make class ratingDTO with attribute rating and count
}
/*
{
        "id": 1,
        "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        "price": 109.95,
        "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
        "category": "men's clothing",
        "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        "ratings" : [1,2,3]
        "rating": {
            "rate": 3.9,
            "count": 120
        }
    },
 */
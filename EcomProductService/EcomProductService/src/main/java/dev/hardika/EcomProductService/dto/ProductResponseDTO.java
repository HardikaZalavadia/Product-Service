package dev.hardika.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {
    private UUID productId;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private double ratings;
}

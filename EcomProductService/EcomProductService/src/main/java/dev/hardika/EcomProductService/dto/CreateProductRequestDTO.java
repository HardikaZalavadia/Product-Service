package dev.hardika.EcomProductService.dto;

import dev.hardika.EcomProductService.entity.Category;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private double price;
    private String description;
    private UUID categoryID;
    private String imageURL;
}

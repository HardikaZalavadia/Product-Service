package dev.hardika.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class CategoryResponseDTO {
    private UUID categoryId;
    private String name;
}

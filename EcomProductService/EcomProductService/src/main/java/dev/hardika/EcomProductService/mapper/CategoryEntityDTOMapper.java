package dev.hardika.EcomProductService.mapper;

import dev.hardika.EcomProductService.dto.CategoryResponseDTO;
import dev.hardika.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.hardika.EcomProductService.entity.Category;

public class CategoryEntityDTOMapper {
    public static Category convertCategoryRequestDTOtoCategoryEntity(CreateCategoryRequestDTO requestDTO){
        Category category = new Category();
        category.setName(requestDTO.getName());
        return category;
    }

    public static CategoryResponseDTO convertCategoryEntityToCategoryResponseDTO(Category category){
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(category.getId());
        categoryResponseDTO.setName(category.getName());
        return categoryResponseDTO;
    }
}

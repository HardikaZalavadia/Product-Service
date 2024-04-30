package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.dto.CategoryResponseDTO;
import dev.hardika.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.hardika.EcomProductService.dto.CreateProductRequestDTO;
import dev.hardika.EcomProductService.dto.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategory(UUID categoryId);
    CategoryResponseDTO createCategory(CreateCategoryRequestDTO requestDTO);
    CategoryResponseDTO updateCategory(CreateCategoryRequestDTO updatedCategory, UUID categoryId);
    boolean deleteCategory(UUID categoryId);
    //CategoryResponseDTO getCategoryByName(String categoryName);
    double getTotlePriceForCategory(UUID categoryID);
}

package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.dto.CategoryResponseDTO;
import dev.hardika.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.hardika.EcomProductService.entity.Category;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.CategoryNotFoundException;
import dev.hardika.EcomProductService.mapper.CategoryEntityDTOMapper;
import dev.hardika.EcomProductService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> responseDTOS = new ArrayList<>();
        for(Category category: categories){
            responseDTOS.add(CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(category));
        }
        return responseDTOS;
    }

    @Override
    public CategoryResponseDTO getCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category is not Present with this Id: "+ categoryId));
        return CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO requestDTO) {
        Category category = CategoryEntityDTOMapper.convertCategoryRequestDTOtoCategoryEntity(requestDTO);
        category = categoryRepository.save(category);
        return CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO updatedCategory, UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException(" Category is not found with this Id: "+ categoryId));
        category.setName(updatedCategory.getName());
        return CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(category);

    }

    @Override
    public boolean deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
       return true;
    }

    /*
    @Override
    public CategoryResponseDTO getCategoryByName(String categoryName) {
        Category category = new Category();

        if(!category.getName().equals(categoryRepository.findCategoryByName(categoryName))){
            new CategoryNotFoundException(" Category is not found with this Id: "+ categoryName);
        }
        category = categoryRepository.findCategoryByName(categoryName);
                return CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(category);
    }
    */
    @Override
    public double getTotlePriceForCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException(" Category is not found with this Id: "+ categoryId));
        if(category.getProducts().isEmpty()){
            return 0;
        }
        else{
            double sum = 0;
            for(Product product : category.getProducts()){
                sum += product.getPrice();
            }
            return sum;
        }
    }
}

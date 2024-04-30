package dev.hardika.EcomProductService.controller;

import dev.hardika.EcomProductService.dto.CategoryResponseDTO;
import dev.hardika.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.hardika.EcomProductService.exception.CategoryInvalidException;
import dev.hardika.EcomProductService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CreateCategoryRequestDTO requestDTO){
        return ResponseEntity.ok(categoryService.createCategory(requestDTO));
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable ("id") UUID id)  {
        if(id==null){
            throw new CategoryInvalidException("Gives valid Id");
        }
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable ("id") UUID id, @RequestBody CreateCategoryRequestDTO requestDTO){
        return ResponseEntity.ok(categoryService.updateCategory(requestDTO,id));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") UUID id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
   /*  not worked have some issues need to solve
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable("categoryName") String name){
        if(name==null || name.isEmpty() || name.isBlank()){
            throw new CategoryInvalidException("Enter valid name of category:");
        }
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }
    */
    @GetMapping("/totalPrice/{id}")
    public ResponseEntity<Double> getPriceForCategory(@PathVariable("id") UUID id){
        return ResponseEntity.ok(categoryService.getTotlePriceForCategory(id));
    }

}

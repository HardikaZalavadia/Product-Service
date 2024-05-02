package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.entity.Category;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.CategoryNotFoundException;
import dev.hardika.EcomProductService.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // It initialises and adds all the required data
    }
    @Test
    public void getTotalPriceForAllProductUnderCategory(){
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryMockData = getCategoryMockData();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryMockData);
        double expectedTotalCost = 500.00;

        //Act
        double actualTotalCost = categoryService.getTotlePriceForCategory(categoryId);

        //Assert
        Assertions.assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    public void testCategoryNotFoundExceptionThrow(){
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        //Act & Assert
        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getTotlePriceForCategory(categoryId));
    }
    @Test
    public void testCategoryTotalPriceOfZeroProductUnderCategory(){
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryMockData = getCategoryOfZeroProduct();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryMockData);
        double expectedTotalCost = 00.00;
        //Act
        double actualTotalCost = categoryService.getTotlePriceForCategory(categoryId);
        //Assert -> All the checks
        Assertions.assertEquals(expectedTotalCost, actualTotalCost);
        Mockito.verify(categoryRepository).findById(categoryId);

    }

    private Optional<Category> getCategoryOfZeroProduct() {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("Zero Product");

        List<Product> products = new ArrayList<>();
        category.setProducts(products);
        return Optional.of(category);
    }

    private Optional<Category> getCategoryMockData() {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("Category Name");

        Product product1 = new Product();
        product1.setTitle("Product 1");
        product1.setId(UUID.randomUUID());
        product1.setPrice(200.00);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setId(UUID.randomUUID());
        product2.setPrice(300.00);
        product2.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        category.setProducts(products);
        return Optional.of(category);
    }
}

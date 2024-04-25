package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.dto.CreateProductRequestDTO;
import dev.hardika.EcomProductService.dto.ProductResponseDTO;
import dev.hardika.EcomProductService.dto.fakeStoreDTO.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Category;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.CategoryNotFoundException;
import dev.hardika.EcomProductService.exception.ProductNotFoundException;
import dev.hardika.EcomProductService.mapper.ProductEntityDTOMapper;
import dev.hardika.EcomProductService.repository.CategoryRepository;
import dev.hardika.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product : products){
            productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product is not present with this ID : "+ productId)
        );
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO productRequestDTO) {
        Product product = ProductEntityDTOMapper.convertCreateProductRequestDTOtoProduct(productRequestDTO);
        Category category = categoryRepository.findById(productRequestDTO.getCategoryID()).orElseThrow(
                ()-> new CategoryNotFoundException("Category Not Found for ID : " + productRequestDTO.getCategoryID()));
        product.setCategory(category);
        product = productRepository.save(product);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);

    }

    @Override
    public ProductResponseDTO updateProduct(CreateProductRequestDTO createProductRequestDTO, UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product is not available for this Id : "+productId));
        product.setTitle(createProductRequestDTO.getTitle());
        product.setImageURL(createProductRequestDTO.getImageURL());
        product.setPrice(createProductRequestDTO.getPrice());
        product.setDescription(createProductRequestDTO.getDescription());
        product = productRepository.save(product);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product is not available for this Id : "+productId));
        productRepository.deleteById(productId);
        return true;
    }
    public ProductResponseDTO getProductByName(String productName){
        Product product = productRepository.findProductByTitle(productName);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getProduct(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice,maxPrice);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product: products){
            productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }
}

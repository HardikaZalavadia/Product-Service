package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.client.FakeStoreClient;
import dev.hardika.EcomProductService.dto.CreateProductRequestDTO;
import dev.hardika.EcomProductService.dto.ProductResponseDTO;
import dev.hardika.EcomProductService.dto.fakeStoreDTO.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.NoProductsPresentException;
import dev.hardika.EcomProductService.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*     This Class is only for understanding of controller Advise
@Service
public class FakeStoreProductServiceImpl implements ProductService{

    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Override
    public List<ProductResponseDTO> getAllProducts() throws NoProductsPresentException{
        List<FakeStoreProductResponseDTO> fakeStoreProductList = fakeStoreClient.getAllProducts();
        if(fakeStoreProductList == null){
            throw new NoProductsPresentException("No Products are Present");
        }
        return fakeStoreProductList;
    }

    @Override
    public ProductResponseDTO getProduct(int productId) throws ProductNotFoundException {
        FakeStoreProductResponseDTO product = fakeStoreClient.getProductById(productId);
        if(product == null){
            throw new ProductNotFoundException("Product Not Found With Id : " + productId);
        }
        return product;
    }

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO product) {
        return null;
    }

    @Override
    public Product updateProduct(Product updatedProduct, int productId) {
        return null;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }
}

 */

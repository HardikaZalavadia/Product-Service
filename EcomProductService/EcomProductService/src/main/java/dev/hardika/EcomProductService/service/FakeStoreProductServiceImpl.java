package dev.hardika.EcomProductService.service;

import dev.hardika.EcomProductService.client.FakeStoreClient;
import dev.hardika.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.hardika.EcomProductService.entity.Product;
import dev.hardika.EcomProductService.exception.NoProductsPresentException;
import dev.hardika.EcomProductService.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FakeStoreProductServiceImpl implements ProductService{

    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Override
    public List<FakeStoreProductResponseDTO> getAllProducts() throws NoProductsPresentException{
        List<FakeStoreProductResponseDTO> fakeStoreProductList = fakeStoreClient.getAllProducts();
        if(fakeStoreProductList == null){
            throw new NoProductsPresentException("No Products are Present");
        }
        return fakeStoreProductList;
    }

    @Override
    public FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException {
        FakeStoreProductResponseDTO product = fakeStoreClient.getProductById(productId);
        if(product == null){
            throw new ProductNotFoundException("Product Not Found With Id : " + productId);
        }
        return product;
    }

    @Override
    public Product createProduct(Product product) {
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

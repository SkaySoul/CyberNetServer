package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.FullProductDTO;
import com.cybernet.cybernetserver.dto.ProductDTO;
import com.cybernet.cybernetserver.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO dto);
    List<ProductDTO> getAllProducts();
    Product updateProduct(ProductDTO dto);
    void deleteProduct(Long id);
    ProductDTO getProductById(Long id);
    FullProductDTO getFullProductById(Long id);
    List<ProductDTO> getByCategoryId(Long id);
    List<Product> getBySubcategoryId(Long subcategoryId);

}

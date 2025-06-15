package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.ProductDTO;
import com.cybernet.cybernetserver.entities.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductDTOConverter {

    private ModelMapper modelMapper;
    public Product mapProductDTOtoProduct(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO mapProductToProductDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }
}

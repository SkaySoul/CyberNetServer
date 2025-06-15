package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.FullProductDTO;
import com.cybernet.cybernetserver.entities.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FullProductDTOConverter {
    private ModelMapper modelMapper;

    public Product mapProductDTOtoProduct(FullProductDTO fullProductDTO){
        return modelMapper.map(fullProductDTO, Product.class);
    }

    public FullProductDTO mapProductToProductDTO(Product product){
        return modelMapper.map(product, FullProductDTO.class);
    }
}

package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.ProductImageDTO;
import com.cybernet.cybernetserver.entities.ProductImage;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductImageDTOConverter {

    private ModelMapper modelMapper;
    public ProductImage mapProductImageDTOtoProductImage(ProductImageDTO productImageDTO){
        return modelMapper.map(productImageDTO, ProductImage.class);
    }

    public ProductImageDTO mapProductImageToProductImageDTO(ProductImage productImage){
        if (productImage == null) {
            return null;
        }
        return modelMapper.map(productImage, ProductImageDTO.class);
    }
}

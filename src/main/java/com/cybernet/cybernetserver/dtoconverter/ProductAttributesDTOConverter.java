package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.ProductAttributesDTO;
import com.cybernet.cybernetserver.entities.ProductAttributes;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductAttributesDTOConverter {

    private ModelMapper modelMapper;

    public ProductAttributes mapProductAttributesDTOtoProductAttributes(ProductAttributesDTO productAttributesDTO){
        return modelMapper.map(productAttributesDTO, ProductAttributes.class);
    }

    public ProductAttributesDTO mapProductAttributesToProductAttributesDTO(ProductAttributes productAttributes){
        return modelMapper.map(productAttributes, ProductAttributesDTO.class);
    }
}
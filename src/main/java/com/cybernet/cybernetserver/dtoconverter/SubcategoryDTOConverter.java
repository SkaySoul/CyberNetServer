package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.SubcategoryDTO;
import com.cybernet.cybernetserver.entities.Subcategory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubcategoryDTOConverter {
    private ModelMapper modelMapper;

    public Subcategory mapSubcategoryDTOtoSubcategory(SubcategoryDTO subcategoryDTO){
        return modelMapper.map(subcategoryDTO, Subcategory.class);
    }

    public SubcategoryDTO mapSubcategoryToSubcategoryDTO(Subcategory subcategory){
        return modelMapper.map(subcategory, SubcategoryDTO.class);
    }
}

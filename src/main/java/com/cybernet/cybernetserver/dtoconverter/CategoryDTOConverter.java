package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.CategoryDTO;
import com.cybernet.cybernetserver.entities.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryDTOConverter {
    private ModelMapper modelMapper;

    public Category mapCategoryDTOtoCategory(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryDTO mapCategoryToCategoryDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }
}

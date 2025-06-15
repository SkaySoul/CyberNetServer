package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.CategoryDTO;
import com.cybernet.cybernetserver.dtoconverter.CategoryDTOConverter;
import com.cybernet.cybernetserver.entities.Category;
import com.cybernet.cybernetserver.repositories.CategoryRepository;
import com.cybernet.cybernetserver.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOConverter categoryDTOConverter;
    public List<CategoryDTO> getAll(){
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category: categoryList){
            categoryDTOList.add(categoryDTOConverter.mapCategoryToCategoryDTO(category));
        }
        return categoryDTOList;
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found " + id));
    }
    public CategoryDTO getCategoryDTOById(Long id){
        return categoryDTOConverter.mapCategoryToCategoryDTO(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found " + id)));
    }
}

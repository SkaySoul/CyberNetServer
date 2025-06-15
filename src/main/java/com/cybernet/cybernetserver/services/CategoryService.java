package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.CategoryDTO;
import com.cybernet.cybernetserver.entities.Category;

import java.util.List;

public interface CategoryService {

    public List<CategoryDTO> getAll();
    public CategoryDTO getCategoryDTOById(Long id);
    public Category getCategoryById(Long id);
}

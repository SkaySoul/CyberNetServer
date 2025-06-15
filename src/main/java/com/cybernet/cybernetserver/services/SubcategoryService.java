package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.SubcategoryDTO;
import com.cybernet.cybernetserver.entities.Subcategory;

import java.util.List;

public interface SubcategoryService {

    public List<SubcategoryDTO> getAll();
    public Subcategory getSubcategoryById(Long id);

    SubcategoryDTO getSubcategoryDTOById(Long id);
}

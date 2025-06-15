package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.SubcategoryDTO;
import com.cybernet.cybernetserver.dtoconverter.SubcategoryDTOConverter;
import com.cybernet.cybernetserver.entities.Subcategory;
import com.cybernet.cybernetserver.repositories.SubcategoryRepository;
import com.cybernet.cybernetserver.services.SubcategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryDTOConverter subcategoryDTOConverter;
    public List<SubcategoryDTO> getAll(){
        List<Subcategory> subcategoryList = subcategoryRepository.findAll();
        List<SubcategoryDTO> categoryDTOList = new ArrayList<>();
        for (Subcategory subcategory: subcategoryList){
            categoryDTOList.add(subcategoryDTOConverter.mapSubcategoryToSubcategoryDTO(subcategory));
        }
        return categoryDTOList;
    }

    public Subcategory getSubcategoryById(Long id){
        return subcategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Subcategory not found " + id));
    }
    public SubcategoryDTO getSubcategoryDTOById(Long id){
        return subcategoryDTOConverter.mapSubcategoryToSubcategoryDTO( subcategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Subcategory not found " + id)));
    }
}

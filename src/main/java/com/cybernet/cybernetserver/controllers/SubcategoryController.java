package com.cybernet.cybernetserver.controllers;


import com.cybernet.cybernetserver.dto.SubcategoryDTO;
import com.cybernet.cybernetserver.services.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subcategories")
@AllArgsConstructor
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @GetMapping
    public ResponseEntity<List<SubcategoryDTO>> readAll(){
        return new ResponseEntity<>(subcategoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(subcategoryService.getSubcategoryDTOById(id));
    }

}
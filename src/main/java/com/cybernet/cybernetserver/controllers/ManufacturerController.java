package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.ManufacturerDTO;
import com.cybernet.cybernetserver.dto.SubcategoryDTO;
import com.cybernet.cybernetserver.services.ManufacturerService;
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
@RequestMapping("/api/v1/manufacturers")
@AllArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerDTO>> getAll(){
        return new ResponseEntity<>(manufacturerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(manufacturerService.getManufacturerById(id));
    }

}

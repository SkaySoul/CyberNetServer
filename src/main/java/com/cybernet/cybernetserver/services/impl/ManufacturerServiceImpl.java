package com.cybernet.cybernetserver.services.impl;


import com.cybernet.cybernetserver.entities.Manufacturer;
import com.cybernet.cybernetserver.repositories.ManufacturerRepository;
import com.cybernet.cybernetserver.services.ManufacturerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getAll(){
        return manufacturerRepository.findAll();
    }

    public Manufacturer getManufacturerById(Long id){
        return manufacturerRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found " + id));
    }
}

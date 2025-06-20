package com.cybernet.cybernetserver.services.impl;


import com.cybernet.cybernetserver.dto.ManufacturerDTO;
import com.cybernet.cybernetserver.dtoconverter.ManufacturerDTOConverter;
import com.cybernet.cybernetserver.entities.Manufacturer;
import com.cybernet.cybernetserver.repositories.ManufacturerRepository;
import com.cybernet.cybernetserver.services.ManufacturerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerDTOConverter manufacturerDTOConverter;
    public List<ManufacturerDTO> getAll(){
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        List<ManufacturerDTO> manufacturerDTOList = new ArrayList<>(manufacturerList.size());
        for (Manufacturer manufacturer : manufacturerList) {
            manufacturerDTOList.add(manufacturerDTOConverter.mapManufacturerToManufacturerDTO(manufacturer));
        }
        return manufacturerDTOList;
    }

    public ManufacturerDTO getManufacturerById(Long id){

        return manufacturerDTOConverter.mapManufacturerToManufacturerDTO(manufacturerRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found " + id)));
    }
}

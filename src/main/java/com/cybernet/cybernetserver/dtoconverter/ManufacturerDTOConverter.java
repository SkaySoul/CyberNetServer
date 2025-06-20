package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.FullProductDTO;
import com.cybernet.cybernetserver.dto.ManufacturerDTO;
import com.cybernet.cybernetserver.entities.Manufacturer;
import com.cybernet.cybernetserver.entities.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManufacturerDTOConverter {
    private ModelMapper modelMapper;

    public Manufacturer mapManufacturerDTOtoManufacturer(ManufacturerDTO manufacturerDTO){
        return modelMapper.map(manufacturerDTO, Manufacturer.class);
    }

    public ManufacturerDTO mapManufacturerToManufacturerDTO(Manufacturer manufacturer){
        return modelMapper.map(manufacturer, ManufacturerDTO.class);
    }
}

package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.ManufacturerDTO;
import com.cybernet.cybernetserver.entities.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerDTOConverter {
    private ModelMapper modelMapper;

    public Manufacturer mapManufacturerDTOtoManufacturer(ManufacturerDTO manufacturerDTO){
        return modelMapper.map(manufacturerDTO, Manufacturer.class);
    }

    public ManufacturerDTO mapManufacturerToManufacturerDTO(Manufacturer manufacturer){
        return modelMapper.map(manufacturer, ManufacturerDTO.class);
    }
}

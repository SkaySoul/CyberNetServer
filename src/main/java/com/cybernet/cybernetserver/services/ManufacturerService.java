package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.ManufacturerDTO;
import com.cybernet.cybernetserver.entities.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    ManufacturerDTO getManufacturerById(Long id);
    List<ManufacturerDTO> getAll();
}

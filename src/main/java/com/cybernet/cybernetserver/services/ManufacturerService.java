package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.ManufacturerDTO;

import java.util.List;

public interface ManufacturerService {
    ManufacturerDTO getManufacturerById(Long id);
    List<ManufacturerDTO> getAll();
}

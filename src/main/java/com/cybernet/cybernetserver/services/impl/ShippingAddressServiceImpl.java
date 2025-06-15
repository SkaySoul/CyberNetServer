package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.ShippingAddressDTO;
import com.cybernet.cybernetserver.dtoconverter.ShippingAddressDTOConverter;
import com.cybernet.cybernetserver.entities.ShippingAddress;
import com.cybernet.cybernetserver.repositories.ShippingAddressRepository;
import com.cybernet.cybernetserver.services.ShippingAddressService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;
    private final ShippingAddressDTOConverter shippingAddressDTOConverter;

    public ShippingAddress getById(Long id){
        return shippingAddressRepository.getById(id);
    }
    @Override
    public ShippingAddressDTO createAddress(ShippingAddressDTO shippingAddressDTO, Long userId) {
        shippingAddressDTO.setUserId(userId);
        ShippingAddress entity = shippingAddressDTOConverter.mapShippingAddressDTOtoShippingAddress(shippingAddressDTO);
        entity = shippingAddressRepository.save(entity);
        return shippingAddressDTOConverter.mapShippingAddressToShippingAddressDTO(entity);
    }

    @Override
    public List<ShippingAddressDTO> findByUserId(Long userId) {
        return shippingAddressRepository.findByUserId(userId).stream()
                .map(shippingAddressDTOConverter::mapShippingAddressToShippingAddressDTO)
                .toList();
    }

    @Override
    public ShippingAddressDTO updateAddress(Long id, ShippingAddressDTO shippingAddressDTO) {
        ShippingAddress existing = shippingAddressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address " + id));
        if (shippingAddressDTO.getAddressLine1() != null) existing.setAddressLine1(shippingAddressDTO.getAddressLine1());
        if (shippingAddressDTO.getAddressLine2() != null) existing.setAddressLine2(shippingAddressDTO.getAddressLine2());
        if (shippingAddressDTO.getCity()         != null) existing.setCity(shippingAddressDTO.getCity());
        if (shippingAddressDTO.getPostalCode()   != null) existing.setPostalCode(shippingAddressDTO.getPostalCode());
        if (shippingAddressDTO.getCountry()      != null) existing.setCountry(shippingAddressDTO.getCountry());
        if (shippingAddressDTO.getPhone()        != null) existing.setPhone(shippingAddressDTO.getPhone());
        existing = shippingAddressRepository.save(existing);
        return shippingAddressDTOConverter.mapShippingAddressToShippingAddressDTO(existing);
    }

    @Override
    public void deleteAddress(Long id) {
        shippingAddressRepository.deleteById(id);
    }
}

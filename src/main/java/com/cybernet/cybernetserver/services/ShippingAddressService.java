package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.ShippingAddressDTO;
import com.cybernet.cybernetserver.entities.ShippingAddress;

import java.util.List;

public interface ShippingAddressService {
    ShippingAddressDTO createAddress(ShippingAddressDTO dto, Long userId);
    List<ShippingAddressDTO> findByUserId(Long userId);
    ShippingAddressDTO updateAddress(Long id, ShippingAddressDTO dto);
    void deleteAddress(Long id);
    public ShippingAddress getById(Long id);
}
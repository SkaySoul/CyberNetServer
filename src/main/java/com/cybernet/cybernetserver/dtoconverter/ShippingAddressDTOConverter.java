package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.ShippingAddressDTO;
import com.cybernet.cybernetserver.entities.ShippingAddress;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShippingAddressDTOConverter {
    private ModelMapper modelMapper;
    public ShippingAddress mapShippingAddressDTOtoShippingAddress(ShippingAddressDTO shippingAddressDTO){
        return modelMapper.map(shippingAddressDTO, ShippingAddress.class);
    }

    public ShippingAddressDTO mapShippingAddressToShippingAddressDTO(ShippingAddress shippingAddress){
        return modelMapper.map(shippingAddress, ShippingAddressDTO.class);
    }
}

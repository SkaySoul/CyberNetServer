package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.AddressDTO;
import com.cybernet.cybernetserver.entities.Address;
import com.cybernet.cybernetserver.repositories.AddressRepository;
import com.cybernet.cybernetserver.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AddressServiceImpl {

    private final AddressRepository addressRepository;
    private UserService userService;
    public Address create(AddressDTO dto, Long customerId){
        return addressRepository.save(Address.builder()
                .country(dto.getCountry())
                .region(dto.getRegion())
                .city(dto.getCity())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .postcode(dto.getPostcode())
                .user(userService.getUserById(customerId))
                .build());
    }

    public List<Address> findAll(){
       return addressRepository.findAll();
    }

}

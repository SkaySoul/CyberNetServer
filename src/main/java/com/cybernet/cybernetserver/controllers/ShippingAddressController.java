package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.ShippingAddressDTO;
import com.cybernet.cybernetserver.services.ShippingAddressService;
import com.cybernet.cybernetserver.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@AllArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService addressService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShippingAddressDTO createAddress(
            Authentication auth,
            @RequestBody ShippingAddressDTO shippingAddressDTO
    ) {
        Long userId = userService.findByUsername(auth.getName()).orElseThrow(() ->
                new RuntimeException("User Not Found" + auth.getName())).getId();
        return addressService.createAddress(shippingAddressDTO, userId);
    }


    @GetMapping
    public List<ShippingAddressDTO> listAddresses(Authentication auth) {
        Long userId = userService.findByUsername(auth.getName()).orElseThrow(() ->
                new RuntimeException("User Not Found" + auth.getName())).getId();
        return addressService.findByUserId(userId);
    }

    @PutMapping("/{addressId}")
    public ShippingAddressDTO updateAddress(
            @RequestBody ShippingAddressDTO shippingAddressDTO,
            @PathVariable Long addressId) {
        return addressService.updateAddress(addressId, shippingAddressDTO);
    }


    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @PathVariable Long addressId
    ) {
        addressService.deleteAddress(addressId);
    }
}
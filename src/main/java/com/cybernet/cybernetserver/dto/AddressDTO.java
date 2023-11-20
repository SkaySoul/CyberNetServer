package com.cybernet.cybernetserver.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressDTO {
    private String country;
    private String region;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
}

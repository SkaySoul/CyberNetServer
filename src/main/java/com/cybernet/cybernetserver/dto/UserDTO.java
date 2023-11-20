package com.cybernet.cybernetserver.dto;

import com.cybernet.cybernetserver.entities.Address;
import com.cybernet.cybernetserver.entities.enums.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.List;

@Data
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private List<Address> addresses;
    private boolean isActive;
    private HashSet<Role> roles;

}

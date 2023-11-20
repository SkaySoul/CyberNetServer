package com.cybernet.cybernetserver.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER_CUSTOMER, USER_EMPLOYEE;

    @Override
    public String getAuthority() {
        return name();
    }
}

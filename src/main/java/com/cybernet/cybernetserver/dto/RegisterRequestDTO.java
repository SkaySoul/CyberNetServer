package com.cybernet.cybernetserver.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String surname;
}

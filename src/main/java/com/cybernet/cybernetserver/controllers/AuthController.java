package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.RegisterRequestDTO;
import com.cybernet.cybernetserver.dto.UserDTO;
import com.cybernet.cybernetserver.dtoconverter.UserDTOConverter;
import com.cybernet.cybernetserver.entities.User;
import com.cybernet.cybernetserver.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOConverter userDTOConverter;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO requestDTO) {
        if (userService.findByUsername(requestDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already in use.");
        }

        User user = new User();
        user.setEmail(requestDTO.getEmail());
        user.setUsername(requestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setName(requestDTO.getName());
        user.setSurname(requestDTO.getSurname());

        userService.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(userDTOConverter.mapUserToUserDTO(user));
    }


}
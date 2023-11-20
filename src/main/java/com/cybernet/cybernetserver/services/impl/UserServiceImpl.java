package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.UserDTO;
import com.cybernet.cybernetserver.entities.User;
import com.cybernet.cybernetserver.repositories.UserRepository;
import com.cybernet.cybernetserver.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public User createUser(UserDTO dto) {
        return userRepository.save(User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .roles(dto.getRoles())
                .isActive(dto.isActive())
                .address(dto.getAddresses())
                .build());
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public List<User> getUsersByUsername(String username) {
        return username == null ? userRepository.findAll() : userRepository.findUserByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Customer Not Found" + id));
    }
}

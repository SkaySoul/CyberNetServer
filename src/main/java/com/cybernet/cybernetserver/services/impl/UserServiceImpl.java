package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.entities.User;
import com.cybernet.cybernetserver.repositories.RoleRepository;
import com.cybernet.cybernetserver.repositories.UserRepository;
import com.cybernet.cybernetserver.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;



    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User Not Found" + id));
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}

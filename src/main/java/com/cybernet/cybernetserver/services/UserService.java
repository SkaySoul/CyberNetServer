package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    Optional<User> findByUsername(String username);

    void deleteUser(Long id);

    User findById(Long id);
    void save(User user);

}

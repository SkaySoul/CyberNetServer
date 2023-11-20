package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.UserDTO;
import com.cybernet.cybernetserver.entities.User;

import java.util.List;

public interface UserService {
    public User createUser(UserDTO dto);

    public List<User> getUser();

    public List<User> getUsersByUsername(String username);

    public User saveUser(User user);

    public void deleteUser(Long id);

    public User getUserById(Long id);
}

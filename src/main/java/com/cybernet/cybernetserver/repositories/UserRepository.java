package com.cybernet.cybernetserver.repositories;

import com.cybernet.cybernetserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findUserByUsername(String username);
}

package com.cybernet.cybernetserver.repositories;

import com.cybernet.cybernetserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username) throws UsernameNotFoundException;
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

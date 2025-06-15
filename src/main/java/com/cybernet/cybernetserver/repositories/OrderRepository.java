package com.cybernet.cybernetserver.repositories;

import com.cybernet.cybernetserver.entities.Order;
import com.cybernet.cybernetserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUserAndStatus(User user, String status);
}

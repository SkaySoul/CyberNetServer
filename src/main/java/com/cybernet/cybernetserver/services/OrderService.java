package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(Long userId, OrderDTO dto);
    OrderDTO getById(Long orderId);

    List<OrderDTO> findAllByUserId(Long userId);
    OrderDTO updateStatus(Long orderId, String status);

}

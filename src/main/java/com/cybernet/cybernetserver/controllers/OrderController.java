package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.OrderDTO;
import com.cybernet.cybernetserver.services.OrderService;
import com.cybernet.cybernetserver.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(Authentication auth, @RequestBody OrderDTO orderDTO) {
        Long userId = userService.findByUsername(auth.getName()).orElseThrow(() ->
                new RuntimeException("User Not Found" + auth.getName())).getId();
        return orderService.createOrder(userId, orderDTO);
    }

    @GetMapping
    public List<OrderDTO> listOrders(Authentication auth) {
        Long userId = userService.findByUsername(auth.getName()).orElseThrow(() ->
                new RuntimeException("User Not Found" + auth.getName())).getId();
        return orderService.findAllByUserId(userId);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable Long orderId) {
        return orderService.getById(orderId);
    }
}

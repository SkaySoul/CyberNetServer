package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.OrderDTO;
import com.cybernet.cybernetserver.dtoconverter.OrderDTOConverter;
import com.cybernet.cybernetserver.entities.*;
import com.cybernet.cybernetserver.repositories.OrderItemRepository;
import com.cybernet.cybernetserver.repositories.OrderRepository;
import com.cybernet.cybernetserver.repositories.ProductRepository;
import com.cybernet.cybernetserver.services.OrderService;
import com.cybernet.cybernetserver.services.ShippingAddressService;
import com.cybernet.cybernetserver.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShippingAddressService shippingAddressService;
    private final OrderDTOConverter orderDTOConverter;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Transactional
    public OrderDTO createOrder(Long userId, OrderDTO dto) {

        User user = userService.findById(userId);
        ShippingAddress address = shippingAddressService.getById(dto.getShippingAddressId());


        Order order = orderDTOConverter.mapOrderDTOtoOrder(dto);

        order.setUser(user);
        order.setShippingAddress(address);
        order.setTotalAmount(BigDecimal.ZERO);
        order.setItems(new ArrayList<>());
        order = orderRepository.save(order);


        Order finalOrder = order;
        List<OrderItem> items = dto.getItems().stream()
                .map(itemDto -> {

                    Product product = productRepository.findById(itemDto.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product " + itemDto.getProductId()));

                    OrderItem it = new OrderItem();
                    it.setOrder(finalOrder);
                    it.setProduct(product);
                    it.setUnitPrice(BigDecimal.valueOf(product.getPrice()));
                    it.setQuantity(itemDto.getQuantity());
                    it.setTotalPrice(it.getUnitPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
                    return orderItemRepository.save(it);
                })
                .collect(Collectors.toList());

        order.setItems(items);


        BigDecimal total = items.stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);

        order = orderRepository.save(order);
        return orderDTOConverter.mapOrderToOrderDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderDTOConverter::mapOrderToOrderDTO)
                .orElseThrow(() -> new EntityNotFoundException("Order " + orderId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAllByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderDTOConverter::mapOrderToOrderDTO)
                .toList();
    }

    @Override
    public OrderDTO updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order " + orderId));
        order.setStatus(status);
        order = orderRepository.save(order);
        return orderDTOConverter.mapOrderToOrderDTO(order);
    }
}

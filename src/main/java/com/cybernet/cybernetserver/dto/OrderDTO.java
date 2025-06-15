package com.cybernet.cybernetserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDateTime createdAt;
    private String status;
    private Long shippingAddressId;
    private List<OrderItemDTO> items;
    private String deliveryMethod;
    private BigDecimal totalAmount;
    private String paymentMethod;
}

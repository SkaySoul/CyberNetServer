package com.cybernet.cybernetserver.services;

import com.cybernet.cybernetserver.dto.CartItemDTO;
import com.cybernet.cybernetserver.entities.CartItem;

import java.util.List;

public interface CartService {
    List<CartItemDTO> getCartDTO(Long userId);
    List<CartItem> getCart(Long userId);
    void addItem(Long userId, Long productId, int qty);

    void removeItem(Long userId, Long productId);

    void clearCart(Long userId);
}

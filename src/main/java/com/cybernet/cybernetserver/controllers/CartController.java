package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.CartItemDTO;
import com.cybernet.cybernetserver.dtoconverter.CartItemDTOConverter;
import com.cybernet.cybernetserver.services.CartService;
import com.cybernet.cybernetserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final CartItemDTOConverter cartItemDTOConverter;

    private Long currentUserId(Authentication auth) {
        String username = auth.getName();
        return userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username))
                .getId();
    }

    @GetMapping
    public List<CartItemDTO> getCart(Authentication auth) {
        Long userId = currentUserId(auth);
        return cartService.getCartDTO(userId);
    }

    @PostMapping
    public void addToCart(@RequestBody CartItemDTO dto, Authentication auth) {
        Long userId = currentUserId(auth);
        cartService.addItem(userId, dto.getProductId(), dto.getQuantity());
    }

    @DeleteMapping("/{productId}")
    public void removeFromCart(@PathVariable Long productId, Authentication auth) {
        Long userId = currentUserId(auth);
        cartService.removeItem(userId, productId);
    }

    @PostMapping("/checkout")
    public void checkout(Authentication auth) {
        Long userId = currentUserId(auth);
        // здесь можно вставить логику оформления заказа...
        cartService.clearCart(userId);
    }
}
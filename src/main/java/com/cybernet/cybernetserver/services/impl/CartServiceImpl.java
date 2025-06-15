package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.CartItemDTO;
import com.cybernet.cybernetserver.dtoconverter.CartItemDTOConverter;
import com.cybernet.cybernetserver.entities.CartItem;
import com.cybernet.cybernetserver.entities.Product;
import com.cybernet.cybernetserver.entities.User;
import com.cybernet.cybernetserver.repositories.CartItemRepository;
import com.cybernet.cybernetserver.repositories.ProductImageRepository;
import com.cybernet.cybernetserver.repositories.ProductRepository;
import com.cybernet.cybernetserver.repositories.UserRepository;
import com.cybernet.cybernetserver.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemRepository repo;
    private final UserRepository userRepo;
    private final ProductRepository prodRepo;
    private final ProductImageRepository productImageRepository;
    private final CartItemDTOConverter cartItemDTOConverter;
    public List<CartItem> getCart(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<CartItemDTO> getCartDTO(Long userId) {
        List<CartItem> cartItems = repo.findByUserId(userId);
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        for (CartItem item: cartItems){

            CartItemDTO cartItemDTO = cartItemDTOConverter.mapCartItemToCartItemDTO(item);
            cartItemDTO.setImageUrl("/images/"+productImageRepository.findByProductIdAndPreviewImageIsTrue(item.getProduct().getId()).getId());
            cartItemDTO.setName(item.getProduct().getName());
            cartItemDTO.setPrice(item.getProduct().getPrice());
            cartItemDTOList.add(cartItemDTO);
        }
        return cartItemDTOList;
    }
    public void addItem(Long userId, Long productId, int qty) {
        User u = userRepo.findById(userId).orElseThrow();
        Product p = prodRepo.findById(productId).orElseThrow();
        CartItem item = repo.findByUserId(userId).stream()
                .filter(ci->ci.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem(null, u, p, 0));
        item.setQuantity(item.getQuantity() + qty);
        repo.save(item);
    }


    public void removeItem(Long userId, Long productId) {
        repo.deleteByUserIdAndProductId(userId, productId);
    }


    public void clearCart(Long userId) {
        repo.deleteAll(getCart(userId));
    }
}

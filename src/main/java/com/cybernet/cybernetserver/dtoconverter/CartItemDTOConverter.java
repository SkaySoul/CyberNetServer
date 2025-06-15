package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.CartItemDTO;
import com.cybernet.cybernetserver.entities.CartItem;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartItemDTOConverter {
    private ModelMapper modelMapper;

    public CartItem mapCartItemDTOtoCartItem(CartItemDTO cartItemDTO){
        return modelMapper.map(cartItemDTO, CartItem.class);
    }

    public CartItemDTO mapCartItemToCartItemDTO(CartItem cartItem){
        return modelMapper.map(cartItem, CartItemDTO.class);
    }
}

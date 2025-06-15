package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.OrderItemDTO;
import com.cybernet.cybernetserver.entities.OrderItem;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderItemDTOConverter {
    private ModelMapper modelMapper;

    public OrderItem mapOrderItemDTOtoOrderItem(OrderItemDTO orderItemDTO){
        return modelMapper.map(orderItemDTO, OrderItem.class);
    }

    public OrderItemDTO mapOrderItemToOrderItemDTO(OrderItem orderItem){
        return modelMapper.map(orderItem, OrderItemDTO.class);
    }
}

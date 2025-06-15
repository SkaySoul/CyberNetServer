package com.cybernet.cybernetserver.dtoconverter;

import com.cybernet.cybernetserver.dto.OrderDTO;
import com.cybernet.cybernetserver.entities.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class OrderDTOConverter {
    private ModelMapper modelMapper;

    public Order mapOrderDTOtoOrder(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }

    public OrderDTO mapOrderToOrderDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }
}

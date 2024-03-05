package org.app.logistics.services;

import org.app.logistics.data.model.OrderItem;
import org.app.logistics.data.repository.OrderItemRepository;
import org.app.logistics.dtos.request.OrderItemDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    private OrderItemRepository orderItemRepository;
    private String[] orderType = new String [5];
    private int size;


    @Override
    public void placeOrder(OrderItemDetailsRequest orderDetailsRequest,String customerId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setType(orderDetailsRequest.getType());
        orderItem.setProductNumber(orderDetailsRequest.getProductNumber());
        orderItem.setDescription(orderDetailsRequest.getDescription());
        orderItem.setCustomerId(customerId);
        orderItemRepository.save(orderItem);
    }
}


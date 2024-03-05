package org.app.logistics.services;

import org.app.logistics.dtos.request.OrderItemDetailsRequest;

public interface OrderItemService {
    void placeOrder(OrderItemDetailsRequest orderItemDetailsRequest, String customerId);
}

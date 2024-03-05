package org.app.logistics.services;

import org.app.logistics.dtos.request.LoginRequest;
import org.app.logistics.dtos.request.OrderItemDetailsRequest;
import org.app.logistics.dtos.request.RegisterRequest;

public interface CustomerService {
    void addCustomer();
    void editCustomer();
    void deleteCustomer();
    void searchCustomer();

    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    void placeOrder(OrderItemDetailsRequest orderDetailsRequest);
}

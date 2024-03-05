package org.app.logistics.utils;

import org.app.logistics.data.model.Customer;
import org.app.logistics.dtos.request.RegisterRequest;

public class Mapper {
    public static Customer map(RegisterRequest registerRequest){
        Customer newCustomer = new Customer();
        newCustomer.setEmail(registerRequest.getEmail());
        newCustomer.setPassword(registerRequest.getPassword());
        return newCustomer;
    }
}

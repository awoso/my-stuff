package org.app.logistics.services;

import org.app.logistics.data.model.Customer;
import org.app.logistics.data.repository.CustomerRepository;
import org.app.logistics.dtos.request.LoginRequest;
import org.app.logistics.dtos.request.OrderItemDetailsRequest;
import org.app.logistics.dtos.request.RegisterRequest;
import org.app.logistics.exceptions.InvalidDetailsException;
import org.app.logistics.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.app.logistics.utils.Mapper.map;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Override
    public void addCustomer() {

    }

    @Override
    public void editCustomer() {

    }

    @Override
    public void deleteCustomer() {

    }

    @Override
    public void searchCustomer() {

    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if(userExist(registerRequest.getEmail())) throw new UserExistException(registerRequest.getEmail()+ "already exist");
        Customer customer = map(registerRequest);
        customerRepository.save(customer);

    }

    @Override
    public void login(LoginRequest loginRequest) {
        Customer findCustomer = customerRepository.findCustomerByEmail(loginRequest.getEmail());
        if(!userExist(loginRequest.getEmail())) throw new InvalidDetailsException("invalid login details");
        if(!findCustomer.getPassword().equals(loginRequest.getPassword())) throw new InvalidDetailsException("invalid login details");
        findCustomer.setLocked(false);
        customerRepository.save(findCustomer);
    }

    @Override
    public void placeOrder(OrderItemDetailsRequest orderDetailsRequest) {
        Customer customer = customerRepository.findCustomerByEmail(orderDetailsRequest.getCustomerName());
        if(customer != null){
            orderItemService.placeOrder(orderDetailsRequest, customer.getId());
        }
    }

    private boolean userExist(String email) {
        Customer findCustomer = customerRepository.findCustomerByEmail(email);
        return findCustomer != null;
    }
}

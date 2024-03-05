package org.app.logistics.services;

import org.app.logistics.data.repository.CustomerRepository;
import org.app.logistics.data.repository.OrderItemRepository;
import org.app.logistics.dtos.request.LoginRequest;
import org.app.logistics.dtos.request.OrderItemDetailsRequest;
import org.app.logistics.dtos.request.RegisterRequest;
import org.app.logistics.exceptions.InvalidDetailsException;
import org.app.logistics.exceptions.UserExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Configuration

class CustomerServiceImplTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @BeforeEach
    public void doThisBeforeEachTest(){
        customerRepository.deleteAll();
    }
    @Test
    public void registerACustomer_countIncreases() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("awoso922@gmail.com");
        registerRequest.setPassword("password");
        customerService.register(registerRequest);
        assertEquals(1,customerRepository.count());
//        assertThrows(UserExistException.class, () -> customerService.register(registerRequest));
    }

    @Test
    public void registerACustomer_registerTheCustomerAgainThrowsException() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("awoso922@gmail.com");
        registerRequest.setPassword("password");
        customerService.register(registerRequest);
        assertThrows(UserExistException.class, () -> customerService.register(registerRequest));
    }
    @Test
    public void testThatARegisteredCustomerCanLogIn(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("awoso922@gmail.com");
        registerRequest.setPassword("password");
        customerService.register(registerRequest);
        loginRequest.setEmail("awoso922@gmail.com");
        loginRequest.setPassword("password");
        customerService.login(loginRequest);
        assertEquals(1,customerRepository.count());
    }
    @Test
    public void testThatWhenARegisteredCustomerLogsInWithWrongDetails_throwsAnException() {
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("awoso922@gmail.com");
        registerRequest.setPassword("password");
        customerService.register(registerRequest);
        loginRequest.setEmail("awoso922@gmail.com.jh");
        loginRequest.setPassword("password");
        assertThrows(InvalidDetailsException.class,() -> customerService.login(loginRequest));
    }
    @Test
    public void testThatWhenACustomerPlacesAnOrder_OrderIncreases(){
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("awoso@gmail.com");
        registerRequest.setPassword("azubuike119");
        customerService.register(registerRequest);
        loginRequest.setEmail("awoso@gmail.com");
        loginRequest.setPassword("azubuike119");
        OrderItemDetailsRequest orderDetailsRequest = new OrderItemDetailsRequest();
        orderDetailsRequest.setType("Edible");
        orderDetailsRequest.setDescription("Soft Drinks");
        orderDetailsRequest.setProductNumber("123gh");
        orderDetailsRequest.setCustomerName("azubuike119");
        customerService.placeOrder(orderDetailsRequest);
        assertEquals(1,orderItemRepository.count());
    }
    @Test
    public void testThatACustomerCAnAddMoreOrder() {
        RegisterRequest registerRequest = new RegisterRequest();
        LoginRequest loginRequest = new LoginRequest();
        registerRequest.setEmail("awoso@gmail.com");
        registerRequest.setPassword("azubuike119");
        customerService.register(registerRequest);
        loginRequest.setEmail("awoso@gmail.com");
        loginRequest.setPassword("azubuike119");
        customerService.login(loginRequest);
        OrderItemDetailsRequest orderDetailsRequest = new OrderItemDetailsRequest();
        orderDetailsRequest.setType("Edible");
        orderDetailsRequest.setDescription("Soft Drinks");
        orderDetailsRequest.setProductNumber("123gh");
        orderDetailsRequest.setCustomerName("azubuike119");
        customerService.placeOrder(orderDetailsRequest);
        orderDetailsRequest.setType("clothing");
        orderDetailsRequest.setDescription("wrist watch");
        orderDetailsRequest.setProductNumber("123gh");
        orderDetailsRequest.setCustomerName("azubuike119");
        customerService.placeOrder(orderDetailsRequest);
        assertEquals(2, orderItemRepository.count());
    }


}
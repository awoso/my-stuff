package org.app.logistics.controllers;

import org.app.logistics.dtos.request.LoginRequest;
import org.app.logistics.dtos.request.RegisterRequest;
import org.app.logistics.dtos.response.ApiResponse;
import org.app.logistics.dtos.response.LoginResponse;
import org.app.logistics.dtos.response.RegisterResponse;
import org.app.logistics.exceptions.LogisticsAppException;
import org.app.logistics.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LogisticsController {
    @Autowired
    protected CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = new RegisterResponse();
        try{
            customerService.register(registerRequest);
            registerResponse.setMessage("registered successfully");
            return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.CREATED);
        }
        catch (LogisticsAppException ex){
            registerResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, registerResponse), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try{
            customerService.login(loginRequest);
            loginResponse.setMessage("login successfully");
            return new ResponseEntity<>(new ApiResponse(false, loginResponse), HttpStatus.CREATED);
        }
        catch (LogisticsAppException ex){
            loginResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, loginResponse), HttpStatus.BAD_REQUEST);
        }
    }
}
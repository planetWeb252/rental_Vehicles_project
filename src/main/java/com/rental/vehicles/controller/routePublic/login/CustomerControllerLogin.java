package com.rental.vehicles.controller.routePublic.login;

import com.rental.vehicles.DTO.customer.login.CustomerDtoLogin;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.services.User.CustomerService;
import com.rental.vehicles.services.JwtServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/login")
public class CustomerControllerLogin {


    private final  CustomerService customerService;

    private final CustomerRepository customerRepository;
    private final JwtServices jwtServices;

    @Autowired
    public CustomerControllerLogin(CustomerService customerService, CustomerRepository customerRepository,
                                   JwtServices jwtServices) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.jwtServices = jwtServices;

    }
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody @Valid CustomerDtoLogin dto) {
       return customerService.loginCustomer(dto);
    }






}

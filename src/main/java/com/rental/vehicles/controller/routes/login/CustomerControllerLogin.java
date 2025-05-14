package com.rental.vehicles.controller.routes.login;

import com.rental.vehicles.DTO.customer.login.CustomerDtoLogin;
import com.rental.vehicles.services.User.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/login")
public class CustomerControllerLogin {

    @Autowired
    private CustomerService customerService;
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody @Valid CustomerDtoLogin dto) {
        return customerService.loginCustomer(dto);
    }

}










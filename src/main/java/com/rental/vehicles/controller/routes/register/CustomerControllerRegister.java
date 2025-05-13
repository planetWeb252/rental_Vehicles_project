package com.rental.vehicles.controller.routes.register;

import com.rental.vehicles.DTO.customer.register.CustomerDTORegister;
import com.rental.vehicles.services.User.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/customer")
public class CustomerControllerRegister {

    private final CustomerService customerService;

    @Autowired
    public CustomerControllerRegister(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTORegister dto) {
       return customerService.createCustomer(dto);
    }




}

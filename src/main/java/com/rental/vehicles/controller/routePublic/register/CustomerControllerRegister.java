package com.rental.vehicles.controller.routePublic.register;

import com.rental.vehicles.models.DTO.customer.CustomerDTORegister;
import com.rental.vehicles.models.DTO.customer.CustomerResponseDTO;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.services.User.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
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

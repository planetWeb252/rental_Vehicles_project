package com.rental.vehicles.controller.UserController;

import com.rental.vehicles.models.DTO.customer.CustomerDTORegister;
import com.rental.vehicles.services.User.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/register")
public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }





    @PostMapping
  public ResponseEntity<CustomerDTORegister> createCustomer(@Valid @RequestBody CustomerDTORegister dto) {
        CustomerDTORegister customerDTO = customerService.createCustomer(dto);
    }
}

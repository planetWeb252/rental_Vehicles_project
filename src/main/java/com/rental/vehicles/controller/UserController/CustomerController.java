package com.rental.vehicles.controller.UserController;

import com.rental.vehicles.models.DTO.CustomerDTO;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.services.User.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }





    @PostMapping
  public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO dto) {

    }
}

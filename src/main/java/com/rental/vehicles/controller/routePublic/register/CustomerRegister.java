package com.rental.vehicles.controller.routePublic.register;

import com.rental.vehicles.models.DTO.customer.CustomerDTORegister;
import com.rental.vehicles.models.DTO.customer.CustomerResponseDTO;
import com.rental.vehicles.services.User.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/customer/register")
public class CustomerRegister {

    private final CustomerService customerService;
    @Autowired
    public CustomerRegister(CustomerService customerService) {
        this.customerService = customerService;
    }





    @PostMapping
  public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerDTORegister dto) {
      return customerService.createCustomer(dto);
    }

}

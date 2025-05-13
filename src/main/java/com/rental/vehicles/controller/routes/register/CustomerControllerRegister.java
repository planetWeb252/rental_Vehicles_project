package com.rental.vehicles.controller.routes.register;

import com.rental.vehicles.DTO.customer.register.CustomerDTORegister;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.services.User.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/public/customer")
public class CustomerControllerRegister {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerControllerRegister(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/allCustomer")
    public ResponseEntity<List<Customer>> allCustomer(){
        return  customerService.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTORegister dto) {
       return customerService.createCustomer(dto);
    }





}

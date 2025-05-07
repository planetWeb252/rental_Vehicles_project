package com.rental.vehicles.controller.routePublic.login;

import com.rental.vehicles.models.DTO.customer.CustomerDTOResponseJWT;
import com.rental.vehicles.models.DTO.customer.CustomerDtoLogin;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.services.User.CustomerService;
import com.rental.vehicles.services.jwtServices.JwtServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("/api/public")
public class CustomerLogin {


    private final  CustomerService customerService;

    private final CustomerRepository customerRepository;
    private final JwtServices jwtServices;

    @Autowired
    public CustomerLogin(CustomerService customerService, CustomerRepository customerRepository, JwtServices jwtServices) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.jwtServices = jwtServices;
    }
    @GetMapping("/login")
    public ResponseEntity<CustomerDTOResponseJWT> login(@RequestBody @Valid CustomerDtoLogin dto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(dto.getEmail());

        if (optionalCustomer.isPresent()) {
            Customer foundCustomer = optionalCustomer.get();
            if (customerService.checkPassword(foundCustomer, dto)) {
                String token = jwtServices.generateToken(foundCustomer.getName());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }






}

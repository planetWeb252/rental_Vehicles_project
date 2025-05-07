package com.rental.vehicles.controller.routePublic.login;

import com.rental.vehicles.exceptions.User.Errormessages;
import com.rental.vehicles.exceptions.User.UserLoginExceptions;
import com.rental.vehicles.models.DTO.customer.CustomerDtoLogin;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.services.User.CustomerService;
import com.rental.vehicles.services.jwtServices.JwtServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public CustomerLogin(CustomerService customerService, CustomerRepository customerRepository,
                          JwtServices jwtServices) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.jwtServices = jwtServices;

    }
    @GetMapping("/login")

    public ResponseEntity<?> login(@RequestBody @Valid CustomerDtoLogin dto) {
        // If the customer is not login
        if (!dto.getRoleCustomer().equals("LOGIN")){
            // find
            Optional<Customer> optionalCustomer = customerRepository.findByEmail(dto.getEmail());
            if (optionalCustomer.isPresent()) {
                Customer foundCustomer = optionalCustomer.get();
                if (customerService.checkPassword(foundCustomer, dto)) {
                    String token = jwtServices.generateToken(foundCustomer.getName());
                    return ResponseEntity.ok(token);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new UserLoginExceptions(Errormessages.INVALID_CUSTOMER_PASSWORD));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserLoginExceptions(Errormessages.CUSTOMER_NOT_FOUND));
            }

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserLoginExceptions(Errormessages.INVALID_CUSTOMER_LOGIN));
        }

    }






}

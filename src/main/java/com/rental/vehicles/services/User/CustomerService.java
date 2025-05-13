package com.rental.vehicles.services.User;

import com.rental.vehicles.enums.ROLE_Customer;
import com.rental.vehicles.DTO.customer.register.CustomerDTORegister;
import com.rental.vehicles.DTO.customer.login.CustomerDtoLogin;
import com.rental.vehicles.DTO.customer.register.CustomerDTOResponseRegister;
import com.rental.vehicles.exceptions.Errormessages;
import com.rental.vehicles.exceptions.User.UserExceptions;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.services.JwtServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtServices;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtServices jwtServices) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtServices = jwtServices;
    }

    public ResponseEntity<?> createCustomer(CustomerDTORegister dto) {
        Optional<Customer> customerEmailFound = customerRepository.findByEmail(dto.getEmail());
        if (customerEmailFound.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        String passEncoder = passwordEncoder.encode(dto.getPassword());
        customer.setPassword(passEncoder);
        try {
            if (dto.getRoleCustomer() == null) {
                customer.setRoleCustomer(ROLE_Customer.ROLE_REGISTER);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("The role is not valid");
        }
        Customer saved = customerRepository.save(customer);
        CustomerDTOResponseRegister customerDTOResponseRegister = new CustomerDTOResponseRegister();
        customerDTOResponseRegister.setName(saved.getName());
        customerDTOResponseRegister.setEmail(saved.getEmail());
        return new ResponseEntity<>(customerDTOResponseRegister, HttpStatus.CREATED);


    }

    public boolean checkPassword(Customer customer, CustomerDtoLogin dto) {

        if (!passwordEncoder.matches(dto.getPassword(), customer.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return true;
    }


    public ResponseEntity<?> loginCustomer(CustomerDtoLogin dto) {
        // find the customer in the bbdd
        Optional<Customer> optionalCustomer = customerRepository.findRoleCustomerByEmail(dto.getEmail());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customer.getRoleCustomer().name().equals("ROLE_REGISTER")) {
                if (checkPassword(customer, dto)) {
                    //if the passs is correct generate the token and save the role in the bbdd
                    String token = jwtServices.generateToken(customer.getName(), customer.getEmail());
                    customer.setRoleCustomer(ROLE_Customer.ROLE_LOGIN);
                    customerRepository.save(customer);
                    return ResponseEntity.status(HttpStatus.OK).body(token);
                } else {
                    // exception the pass is incorrect
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserExceptions(Errormessages.INVALID_CUSTOMER_PASSWORD));
                }
            } else {
                // exception the role is not valid
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserExceptions(Errormessages.INVALID_CUSTOMER_LOGIN));
            }
        } else {
            // exception the customer is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserExceptions(Errormessages.CUSTOMER_NOT_FOUND));
        }


    }

    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers=customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}

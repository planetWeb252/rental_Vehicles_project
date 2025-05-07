package com.rental.vehicles.services.User;

import com.rental.vehicles.enums.ROLE_Customer;
import com.rental.vehicles.models.DTO.customer.CustomerDTORegister;
import com.rental.vehicles.models.DTO.customer.CustomerDtoLogin;
import com.rental.vehicles.models.DTO.customer.CustomerResponseDTO;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> createCustomer(CustomerDTORegister dto) {
        Optional<Customer>customerEmailFound= customerRepository.findByEmail(dto.getEmail());
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
                customer.setRoleCustomer(ROLE_Customer.valueOf("ROLE_REGISTER"));
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("The role is not valid");
        }
        Customer saved = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName(saved.getName());
        customerResponseDTO.setRoleCustomer(String.valueOf(saved.getRoleCustomer()));
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);


    }

    public boolean checkPassword(Customer customer, CustomerDtoLogin dto) {
        if (!passwordEncoder.matches(dto.getPassword(), customer.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return true;
    }


}

package com.rental.vehicles.services.User;

import com.rental.vehicles.models.DTO.customer.CustomerDTORegister;
import com.rental.vehicles.models.DTO.customer.CustomerResponseDTO;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<CustomerResponseDTO> createCustomer(CustomerDTORegister dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        String passEncoder = passwordEncoder.encode(dto.getPassword());
        customer.setPassword(passEncoder);
        Customer saved=customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setName(saved.getName());
        customerResponseDTO.setSurname(saved.getSurname());
        customerResponseDTO.setEmail(saved.getEmail());
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);


    }


}

package com.rental.vehicles.services.User;

import com.rental.vehicles.models.DTO.customer.CustomerDTORegister;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public CustomerDTORegister createCustomer(CustomerDTORegister dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        String passEncoder = passwordEncoder.encode(dto.getPassword());
        customer.setPassword(passEncoder);
        customerRepository.save(customer);
        return new CustomerDTORegister(customer.getId(), customer.getName(),
                customer.getSurname(), customer.getEmail(), customer.getPhone(), customer.getAddress(), passEncoder);


    }


}

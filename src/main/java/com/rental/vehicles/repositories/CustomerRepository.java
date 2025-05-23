package com.rental.vehicles.repositories;

import com.rental.vehicles.models.modelsClass.User.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findRoleCustomerByEmail(String email);


}

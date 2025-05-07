package com.rental.vehicles.models.modelsClass.User;

import com.rental.vehicles.enums.ROLE_Customer;
import com.rental.vehicles.models.modelsAbstrac.User;
import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.Rental;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Customer extends User {

    @OneToMany
    private List<Rental> rentals;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLE_Customer roleCustomer= ROLE_Customer.ROLE_LOGOUT;

    public Customer(Long id, String name, String surname, int phone, String address, String email, List<Rental> rentals, String password) {
        super(id, name, surname, phone, address, email);
        this.rentals = rentals;
        this.password = password;
    }

    public Customer() {
    }
}




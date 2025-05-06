package com.rental.vehicles.models.modelsClass.User;

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

    public Customer(Long id, String name, String surname, int phone, String address, String email, List<Rental> rentals, String password) {
        super(id, name, surname, phone, address, email);
        this.rentals = rentals;
        this.password = password;
    }

    public Customer() {
    }
}




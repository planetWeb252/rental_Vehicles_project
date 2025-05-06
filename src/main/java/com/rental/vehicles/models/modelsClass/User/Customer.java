package com.rental.vehicles.models.modelsClass.User;

import com.rental.vehicles.models.modelsAbstrac.User;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Customer extends User {


    public Customer(Long id, String name, String surname, int phone, String address, String email, String password) {
        super(id, name, surname, phone, address, email, password);
    }

    public Customer() {
    }
}




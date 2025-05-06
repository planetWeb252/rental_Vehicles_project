package com.rental.vehicles.models.modelsClass;

import com.rental.vehicles.models.modelsAbstrac.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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




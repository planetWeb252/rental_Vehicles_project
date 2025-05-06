package com.rental.vehicles.models.modelsClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee extends User {

    @Id
    private Long id;


}

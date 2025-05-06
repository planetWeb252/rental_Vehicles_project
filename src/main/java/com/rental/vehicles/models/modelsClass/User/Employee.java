package com.rental.vehicles.models.modelsClass.User;

import com.rental.vehicles.enums.Role_Employee;
import com.rental.vehicles.models.modelsAbstrac.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Entity
@Data

public class Employee extends User {

    @Enumerated(EnumType.STRING)
    private Role_Employee roleEmployee;

    public Employee(Long id, String name, String surname, int phone, String address, String email, Role_Employee roleEmployee) {
        super(id, name, surname, phone, address, email);
        this.roleEmployee = roleEmployee;
    }

    public Employee() {

    }
}

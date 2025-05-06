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

    public Employee(Role_Employee roleEmployee) {
        this.roleEmployee = roleEmployee;
    }
    public Employee() {

    }
}

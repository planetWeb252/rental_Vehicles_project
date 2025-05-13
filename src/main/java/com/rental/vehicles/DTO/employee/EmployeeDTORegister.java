package com.rental.vehicles.DTO.employee;

import com.rental.vehicles.enums.Role_Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTORegister {

    @NotNull(message = "The name cannot be empty")
    private String name;
    @NotNull(message = "The surname cannot be empty")
    private String surname;
    @NotNull(message = "The email cannot be empty or null")
    @Email(message = "The email is not valid")
    private String email;
    @NotNull(message = "The phone cannot be empty")
    private int phone;
    @NotNull(message = "The address cannot be empty")
    private String address;
    private String adminEmail;
    private Role_Employee role;


}

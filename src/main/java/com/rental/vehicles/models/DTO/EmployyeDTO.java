package com.rental.vehicles.models.DTO;

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
public class EmployyeDTO {
    @NotBlank(message = "The id cannot be empty")
    private Long id;
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @NotBlank(message = "The surname cannot be empty")
    private String surname;
    @NotBlank(message = "The email cannot be empty or null")
    @Email(message = "The email is not valid")
    private String email;
    @NotBlank(message = "The phone cannot be empty")
    private int phone;
    @NotBlank(message = "The address cannot be empty")
    private String address;
    @NotNull(message = "The role cannot be empty")
    private Role_Employee role;


}

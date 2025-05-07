package com.rental.vehicles.models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
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
    @NotBlank(message = "The password cannot be empty")
    private String password;


}

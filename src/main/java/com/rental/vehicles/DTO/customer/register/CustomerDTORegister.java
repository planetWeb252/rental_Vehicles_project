package com.rental.vehicles.DTO.customer.register;

import com.rental.vehicles.enums.ROLE_Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTORegister {
    @NotNull(message = "The name cannot be empty")
    private String name;
    @NotNull(message = "The surname cannot be empty")
    private String surname;
    @NotNull(message = "The email cannot be empty or null")
    @Email(message = "The email is not valid")
    private String email;
    @NotNull(message = "The phone cannot be empty or null")
    private Integer phone;
    @NotNull(message = "The address cannot be empty")
    private String address;
    @NotNull(message = "The password cannot be empty")
    private String password;
    private ROLE_Customer roleCustomer;


}

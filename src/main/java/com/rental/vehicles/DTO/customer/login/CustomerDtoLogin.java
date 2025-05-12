package com.rental.vehicles.DTO.customer.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoLogin {
    @NotNull(message = "The email cannot be empty or null")
    private String name;
    @NotNull(message = "The email cannot be empty or null")
    private String email;
    @NotNull(message = "The role cannot be empty or null")
    private String roleCustomer;
    @NotNull(message = "The password cannot be empty or null")
    private String password;

}

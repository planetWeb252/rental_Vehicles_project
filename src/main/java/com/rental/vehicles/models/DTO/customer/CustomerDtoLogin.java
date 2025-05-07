package com.rental.vehicles.models.DTO.customer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoLogin {
    @NotNull(message = "The email cannot be empty or null")
    private String name;
    @NotNull(message = "The email cannot be empty or null")
    private String email;
    @NotNull(message = "The password cannot be empty or null")
    private String password;
    private String roleCustomer;
}

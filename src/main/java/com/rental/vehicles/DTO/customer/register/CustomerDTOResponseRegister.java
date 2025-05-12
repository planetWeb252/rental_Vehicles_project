package com.rental.vehicles.DTO.customer.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTOResponseRegister {
    private String message="Customer created successfully";
    private String name;
    private String email;
}

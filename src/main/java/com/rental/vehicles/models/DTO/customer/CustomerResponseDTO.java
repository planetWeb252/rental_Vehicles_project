package com.rental.vehicles.models.DTO.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private String message="Customer created successfully";
    private String name;
    private String roleCustomer;
}

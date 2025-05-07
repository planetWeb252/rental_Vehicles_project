package com.rental.vehicles.models.DTO.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String roleCustomer;
}
